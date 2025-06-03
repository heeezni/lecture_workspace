package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		//컬렉션 프레임 웍 중 순서가 없는 유형 중 Map을 알아보자!
		//<Key:Value>
		//HashMap, Hashtable, Properties, Treemap
		Map<String, String> map = new HashMap<>();
		map.put("a1", "가나 초콜릿");
		map.put("a2", "허쉬 초콜릿");
		map.put("a3", "페레로로쉐");
		
		Set set= map.keySet(); //key만 모아놓음
		Iterator<String>it=set.iterator(); //✅iterator⭐
		while(it.hasNext()) {
			String key= it.next();
			String value = map.get(key); //기존 맵에서 key를 이용하여 접근
			System.out.println(key + ":" + value); 
		}
	}
}
