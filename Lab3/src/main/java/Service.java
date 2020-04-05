public class Service {

    private boolean passaneger_transport;
    private boolean cargo_transport;
    private boolean launch_satellite;

    public Service(String type) throws Exception {
        this.passaneger_transport = false;
        this.cargo_transport = false;
        this.launch_satellite = false;
        if(type.equals("PT") || type.equals("pt")){
            this.passaneger_transport = true;
        }
        else if(type.equals("CT") || type.equals("ct")){
            this.cargo_transport = true;
        }
        else if(type.equals("LS") || type.equals("ls")){
            this.launch_satellite = true;
        }
        else{
            System.out.println("Incorrect service type. Choose one of available: PT, CT, LS");
        }
    }

    public boolean isValid(){ // trzeba zmienic nazwe chyba
        return passaneger_transport || cargo_transport || launch_satellite;
    }

    public boolean isPassaneger_transport() {
        return passaneger_transport;
    }

    public boolean isCargo_transport() {
        return cargo_transport;
    }

    public boolean isLaunch_satellite() {
        return launch_satellite;
    }

    public String getName(){
        if(passaneger_transport){
            return "Passenger transport";
        }
        else if(cargo_transport){
            return "Cargo transport";
        }
        else{
            return "Launch satellite";
        }
    }

    public String getType(){
        if(passaneger_transport){
            return "PT";
        }
        else if(cargo_transport){
            return "CT";
        }
        else{
            return "LS";
        }
    }

}
