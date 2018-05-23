package com.yangyang.jsontree;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;


/**
 *
 * @company
 * @author superboo
 * @version 3.0
 * @date 2014-5-21 上午09:45:51
 */
public class JsonTree {
    public static int ID=0;
    public String getJsonStr2() {
        String jsonStr = "{" +
                "  'data': {" +
                "    'yesterday': {" +
                "      'date': '22日星期二'," +
                "      'high': '高温 18℃'," +
                "      'fx': '东南风'," +
                "      'low': '低温 13℃'," +
                "      'fl': '<![CDATA[4-5级]]>'," +
                "      'type': '中雨'" +
                "    }," +
                "    'city': '威海'," +
                "    'forecast': [" +
                "      {" +
                "        'date': '23日星期三'," +
                "        'high': '高温 24℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 15℃'," +
                "        'fengxiang': '北风'," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '24日星期四'," +
                "        'high': '高温 29℃'," +
                "        'fengli': '<![CDATA[6-7级]]>'," +
                "        'low': '低温 18℃'," +
                "        'fengxiang': '西南风'," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '25日星期五'," +
                "        'high': '高温 25℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 16℃'," +
                "        'fengxiang': '南风'," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '26日星期六'," +
                "        'high': '高温 25℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 18℃'," +
                "        'fengxiang': '南风'," +
                "        'type': '多云'" +
                "      }," +
                "      {" +
                "        'date': '27日星期天'," +
                "        'high': '高温 24℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 15℃'," +
                "        'fengxiang': '南风'," +
                "        'type': '小雨'" +
                "      }" +
                "    ]," +
                "    'ganmao': '虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。'," +
                "    'wendu': '18'" +
                "  }," +
                "  'status': 1000," +
                "  'desc': 'OK'" +
                "}";
        return jsonStr;
    }
    @SuppressWarnings("rawtypes")
    public void  analysisJson(Object objJson,String parent_type){
        //如果obj为json数组

        if(objJson instanceof JSONArray){
            JSONArray objArray = (JSONArray)objJson;

                analysisJson(objArray.get(0),"Array");

        }
        //如果为json对象
        else if(objJson instanceof JSONObject){
            JSONObject jsonObject = (JSONObject)objJson;
            Iterator it = jsonObject.keySet().iterator();
            while(it.hasNext()){
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if(object instanceof JSONArray){
                    JSONArray objArray = (JSONArray)object;
                    analysisJson(objArray,"Array");
                }
                //如果key中是一个json对象
                else if(object instanceof JSONObject){
                    analysisJson((JSONObject)object,"Object");
                }
                //如果key中是其他
                else{
                    ID++;
                    System.out.println(ID+"------"+"["+key+"]:"+object.toString()+" "+parent_type);
                }
            }
        }
    }
    public static void main(String[] args) {
        JsonTree hw = new JsonTree();
        JSONObject jsonObject = JSONObject.parseObject(hw.getJsonStr2());
        hw.analysisJson(jsonObject,"Object");
    }
}
