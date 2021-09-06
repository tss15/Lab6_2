package utilities;

import data.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionManager {

    private static HashMap<Integer, LabWork> listLabWork = new HashMap<>();
    private static final FileParser fileParser = new FileParser();
    private static final LocalDateTime creationDate = LocalDateTime.now();
    private static String fileName;
    public static HashSet<Long> IDChecker = new HashSet<>();

    public static void setFileName(String fileName) {
        CollectionManager.fileName = fileName;
    }

    public static void readInputFromJsonFile(){
        listLabWork = fileParser.parse(fileName);
    }

    public static int getNumberOfLabWork(){
        return listLabWork.size();
    }

    public static LocalDateTime getCreationDate(){
        return creationDate;
    }

    public static String info(){
        String response = "";
        response += "Collection's type: " + listLabWork.getClass().getSimpleName() + '\n';
        response += "Initialization date: "  + CollectionManager.getCreationDate() + '\n';
        response += "Collection's size: " + CollectionManager.getNumberOfLabWork();
        return response;
    }

    public static String show(){
        StringBuilder str = new StringBuilder();
        if(listLabWork.size() == 0)  str.append("Collection is empty!");
        else listLabWork.entrySet().stream().sorted().forEach(p -> str.append(p.toString()));
        return String.valueOf(str);
    }

    public static String clear(){
        listLabWork.clear();
        return "Collection is clear!";
    }

    public static String add(Object o){
        Random r = new Random();
        Integer randomVal = Math.abs(r.nextInt());
        listLabWork.put(randomVal, (LabWork) o);
        return "New labwork is added into collection!";
    }


    public static String countByDifficulty(){
        StringBuilder stringBuilder = new StringBuilder();
        listLabWork.keySet().forEach(p -> stringBuilder.append(listLabWork.get(p).getDifficulty()).append(" has 1 element\n"));
        return String.valueOf(stringBuilder);
    }

    public static String printField(){
        StringBuilder stringBuilder = new StringBuilder();

        listLabWork.entrySet().stream().sorted().forEach(p -> stringBuilder.append(listLabWork.get(p).getAuthor()).append('\n'));

        return String.valueOf(stringBuilder);
    }

//    public static String removeByKey(long id){
//        for(Iterator<LabWork> iterator = listLabWork.iterator(); iterator.hasNext();){
//            LabWork labWork = iterator.next();
//            if(labWork.getId() == id){
//                iterator.remove();
//                return "LabWork with ID = " + id + " is removed!";
//            }
//        }
//        return "ID doesn't exist!";
//    }
    public static String removeByKey(long id){
        if(listLabWork.containsKey(id)) {
            listLabWork.remove(id);
            return "LabWork with ID = " + id + " is removed!";
        }
        return "ID doesn't exist!";
    }


    public static String replaceIfGreater(LabWork l){
        listLabWork.values().removeIf(labWork -> labWork.getId() > l.getId());
        return "Removed all greater labworks!";
    }

    public static String replaceIfLower(LabWork l){
        listLabWork.values().removeIf(labWork -> labWork.getId() < l.getId());
        return "Removed all lower labworks";
    }

    public static String update(long id, LabWork o){
        if (listLabWork.containsKey(id)) {
            listLabWork.replace((int) id, o);
            return "Labwork with ID = " + id + " is updated!";
        }
        return "ID doesn't exist!";
    }
    public static void save(){
        JSONArray LabWorkList = new JSONArray();
        for(LabWork labWork : listLabWork.values()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",labWork.getId());
            jsonObject.put("name", labWork.getName());

            /*
             * write coordinate objects into file
             */
            JSONObject coordinatesObj = new JSONObject();
            coordinatesObj.put("x",labWork.getCoordinates().getX());
            coordinatesObj.put("y",labWork.getCoordinates().getY());
            jsonObject.put("coordinates", coordinatesObj);

            /*
             * write creation date into file
             */
            LocalDateTime date = labWork.getCreationDate();
            DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE_TIME;
            String stringDateTime = date.format(fmt);
            jsonObject.put("creationDate", stringDateTime);

            jsonObject.put("minimalPoint", labWork.getMinimalPoint());

            JSONObject locationObj = new JSONObject();
            locationObj.put("x", labWork.getLocation().getX());
            locationObj.put("y",labWork.getLocation().getY());
            locationObj.put("name", labWork.getLocation().getName());
            jsonObject.put("location", locationObj);
            JSONObject personObj = new JSONObject();
            personObj.put("name", labWork.getAuthor().getName());
            personObj.put("weight", labWork.getAuthor().getWeight());
            personObj.put("eye color", labWork.getAuthor().getEyeColor());
            personObj.put("hair color", labWork.getAuthor().getHairColor());
            personObj.put("nationality", labWork.getAuthor().getNationality());
            personObj.put("location", labWork.getAuthor().getLocation());
            jsonObject.put("author", personObj);
//            Random r = new Random();
//            Integer randomVal = Math.abs(r.nextInt());

            LabWorkList.add(jsonObject);
        }

        /*
            write into output file
         */
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.write(LabWorkList.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}