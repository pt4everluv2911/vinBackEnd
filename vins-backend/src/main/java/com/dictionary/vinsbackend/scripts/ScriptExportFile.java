package com.dictionary.vinsbackend.scripts;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class ScriptExportFile {

    public static void main(String[] args){
        exportCSVfile("D:\\sentences.csv", "D:\\links.csv", "D:\\sentences_with_audio.csv", "D:\\result.csv");
    }

    public static void exportCSVfile(final String sentenceFileName,
                                     final String linksFileName,
                                     final String audioFileName,
                                     final String pathResultFile){

        Map<String, String> sentenceEnMap = new HashMap<>();
        Map<String, String> sentenceViMap = new HashMap<>();
        Map<String, String> linksMap = new HashMap<>();
        Map<String, String> audioMap = new HashMap<>();
        final String splitBy ="\t";


        // read file sentences
        try{
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(sentenceFileName));
            while ((line = br.readLine()) != null){
                String[] arrays = line.split(splitBy);
                int length = arrays.length;
                if(length < 3) continue;
                if(arrays[1].equals("eng")) {
                    sentenceEnMap.put(arrays[0], arrays[2]);
                }else if(arrays[1].equals("vie")){
                    sentenceViMap.put(arrays[0], arrays[2]);
                }
            }

        } catch (Exception e) {
            System.out.println("Read file sentence fail!");
            e.printStackTrace();
        }

        //read file links
        try{
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(linksFileName));
            while ((line = br.readLine()) != null){
                String[] arrays = line.split(splitBy);
                int length = arrays.length;
                if(length < 2) continue;
                if(sentenceEnMap.keySet().contains(arrays[0]) && sentenceViMap.keySet().contains(arrays[1])){
                    linksMap.put(arrays[0], arrays[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("Read file links fail!");
            e.printStackTrace();
        }


        //read file audio
        try{
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(audioFileName));
            while ((line = br.readLine()) != null){
                String[] arrays = line.split(splitBy);
                int length = arrays.length;
                if(length < 4) continue;
                if(linksMap.keySet().contains(arrays[0])){
                    if(arrays[2].isEmpty() && (arrays[3].isEmpty() || arrays[3].isBlank())){
                        audioMap.put(arrays[0], "https://audio.tatoeba.org/sentences/eng/"+arrays[0]+".mp3");
                    }else {
                        audioMap.put(arrays[0], arrays[3]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Read file audio fail!");
            e.printStackTrace();
        }

        // export csv file
        File file = new File(pathResultFile);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { "ID_ENG", "TEXT_ENG", "URL", "ID_VIE", "TEXT_VIE" };
            writer.writeNext(header);

            for (Map.Entry<String, String> entry : linksMap.entrySet()) {
                final String idEngSentence = entry.getKey(); // -- ID Eng
                final String idVieSentence = entry.getValue(); // -- ID Vie
                final String engText = sentenceEnMap.get(idEngSentence); // -- English Text
                final String vieText = sentenceViMap.get(idVieSentence); // -- Vietnamese Text
                final String url = audioMap.get(idEngSentence); // -- URL Audio
                System.out.println(idEngSentence + " - " + engText + " - " + audioMap.get(idEngSentence) + " - " + idVieSentence + " - " + vieText);
                String[] data = { idEngSentence, engText, url, idVieSentence,  vieText };
                writer.writeNext(data);
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Export file result fail!");
            e.printStackTrace();
        }
        System.out.println("Export file result successfully!");

    }
}
