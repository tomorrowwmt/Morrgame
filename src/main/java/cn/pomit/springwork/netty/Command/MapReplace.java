package cn.pomit.springwork.netty.Command;


import java.util.HashMap;
import java.util.Map;


public class MapReplace {
        private static Map<String, String> map = new HashMap<String, String>() ;
        public void processAllthings(){
                init();
            }
        private void init(){
        map.put("login","loginmethod");
        map.put("aoi","aoimethod");
        map.put("move","movemethod");
        map.put("talk","talkmethod");
     }
        public void process(String type){
            for(Map.Entry<String,String> processFalg:map.entrySet()){
                if(processFalg.getKey().equals(type)){
                    System.out.println(processFalg.getValue());
                }
            }
        }
}

