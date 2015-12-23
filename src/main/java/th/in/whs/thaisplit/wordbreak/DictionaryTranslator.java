package th.in.whs.thaisplit.wordbreak;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Nut on 12/23/2015 AD.
 */
public class DictionaryTranslator {
    protected JSONArray dictionary;
    private static DictionaryTranslator dictionaryTranslator = new DictionaryTranslator();

    protected DictionaryTranslator(){
        JSONParser parser = new JSONParser();
        try {
            dictionary = (JSONArray) parser.parse(new FileReader(getDictionary()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DictionaryTranslator getInstance(){
        return dictionaryTranslator;
    }

    protected File getDictionary(){
        ClassLoader classLoader = getClass().getClassLoader();
        String dictionary_location = "th/in/whs/thaisplit/wordbreak/thai2eng.json";
        return new File(classLoader.getResource(dictionary_location).getFile());
    }

    public String search_meaning (String search){
        for(Object word : dictionary){
            if(((JSONObject)word).get("search") == null){
                break;
            }
            if(((JSONObject)word).get("search").equals(search)){
                return ((JSONObject)word).get("result").toString();
            }
        }
        return "Meaning not found";
    }
    public static void main(String[] args){
        String print = dictionaryTranslator.search_meaning("ยา");
        System.out.println(print);
    }
}
