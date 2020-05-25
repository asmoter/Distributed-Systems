import java.io.IOException;
import java.util.List;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import static org.apache.zookeeper.Watcher.Event.EventType.*;


public class DataMonitor implements Watcher {

    private final ZooKeeper zk;
    private String znode;
    private DataMonitorListener listener;

    public DataMonitor(String address, String znode, DataMonitorListener listener) throws IOException {

        this.zk = new ZooKeeper(address, 3000, this);
        this.znode = znode;
        this.listener = listener;

        monitorNode();
    }

    public interface DataMonitorListener {
        void handleNodeChange(boolean exists);
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.Expired) {
            exit();
        }
        else if (event.getType() == NodeCreated || event.getType() == NodeDeleted){
            monitorNode();
        }
        else if (event.getType() == NodeChildrenChanged){
            monitorChild();
        }
    }

    private void monitorNode() {
        try {
            Stat stat = zk.exists(znode, true);
            if (stat != null) {
                listener.handleNodeChange(true);
                monitorChild();
            }
            else listener.handleNodeChange(false);
        } catch (KeeperException e) {
            exit();
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void monitorChild() {
        int counter = countChildren(znode) - 1;
        System.out.println("Current children number: " + counter);
    }

    public int countChildren(String znode) {
        int counter = 1;
        try {
            List<String> children = zk.getChildren(znode, false);
            for (String child : children) {
                counter += countChildren(znode + "/" + child);
            }
        } catch (KeeperException e) {
            exit();
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return counter;
    }

    public void printTree(String znode){
        try {
            List<String> children = zk.getChildren(znode, false);
            System.out.println(znode);
            for(String child: children) {
                printTree(znode + "/" + child);
            }
        } catch (KeeperException.NoNodeException e){
            System.out.println("Node does not exist");
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.out.println("Lost connection...");
        System.exit(-1);
    }
}
