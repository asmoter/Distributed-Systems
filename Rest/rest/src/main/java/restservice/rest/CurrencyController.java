package restservice.rest;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String init() {
        return "init";
    }

    @GetMapping(value = "/convert", produces = MediaType.TEXT_HTML_VALUE)
    public String convert(@RequestParam(name = "to") String to, @RequestParam(name = "base") String base, Model model) {

        if (to.equals(base)) {
            model.addAttribute("from", base);
            model.addAttribute("to", to);
            model.addAttribute("min", 1);
            model.addAttribute("max", 1);
            model.addAttribute("avg", 1);
            model.addAttribute("med", 1);
            return "convert";
        }

        Map<String, String> stats = calculate(base, to);

        model.addAttribute("from", base);
        model.addAttribute("to", to);
        model.addAttribute("min", stats.get("min"));
        model.addAttribute("max", stats.get("max"));
        model.addAttribute("avg", stats.get("avg"));
        model.addAttribute("med", stats.get("med"));
        return "convert";
    }

    @GetMapping(value = "/convert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String convertJson(@RequestParam(name = "base") String base, @RequestParam(name = "to") String to) {

        Map<String, String> rates;

        if (base.equals(to)) {
            rates = new HashMap<>();
            rates.put("from", base);
            rates.put("to", to);
            rates.put("min", "1");
            rates.put("max", "1");
            rates.put("avg", "1");
            rates.put("med", "1");
            return new JSONObject(rates).toString();
        }

        rates = calculate(base, to);
        rates.put("from", base);
        rates.put("to", to);

        return new JSONObject(rates).toString();
    }

    private Map<String, String> calculate(String base, String to){

        Currency currency = new Currency(base, to);
        Operations op = new Operations();

        ArrayList<Double> exchangeRates = currency.getValues();

        final RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonObject;
        String url;
        Double value;

        url = "https://api.exchangeratesapi.io/latest?base=" + base;
        jsonObject = new JSONObject(restTemplate.getForObject(url, String.class));
        value = (Double) jsonObject.getJSONObject("rates").get(to);
        exchangeRates.add(value);

        url = "https://api.frankfurter.app/latest?amount=1&from=" + base + "&to=" + to;
        jsonObject = new JSONObject(restTemplate.getForObject(url, String.class));
        value = (Double) jsonObject.getJSONObject("rates").get(to);
        exchangeRates.add(value);

        url = "https://prime.exchangerate-api.com/v5/5617a26a65b08d16d1a5a7d5/latest/" + base;
        jsonObject = new JSONObject(restTemplate.getForObject(url, String.class));
        value = (Double) jsonObject.getJSONObject("conversion_rates").get(to);
        exchangeRates.add(value);

        if(base.equals("USD")){
            url = "http://api.currencylayer.com/live?access_key=1e9fcdbca7f6b6b66a4f5026f0560387" +
                    "&currencies=" + to + "&format=1";
            jsonObject = new JSONObject(restTemplate.getForObject(url, String.class));
            value = (Double) jsonObject.getJSONObject("quotes").get(base+to);
            exchangeRates.add(value);
        }

        if(base.equals("USD")){
            url = "https://currencyapi.net/api/v1/rates?key=9c27e0b54529b2153fd41e9b3902d8c2c929&base=USD";
            jsonObject = new JSONObject(restTemplate.getForObject(url, String.class));
            value = (Double) jsonObject.getJSONObject("rates").get(to);
            exchangeRates.add(value);
        }

        Map<String, String> results = new HashMap<>();
        results.put("avg", String.valueOf(op.average(exchangeRates)));
        results.put("min", String.valueOf(op.minimum(exchangeRates)));
        results.put("max", String.valueOf(op.maximum(exchangeRates)));
        results.put("med", String.valueOf(op.median(exchangeRates)));

        return results;
    }
}
