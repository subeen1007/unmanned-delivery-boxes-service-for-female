import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	//여성안심 무인택배함 데이터를 가져옴
	//불러온 데이터는 여러개이기 때문에 결과값은 Board 정보를 가지는 ArrayList로 반환한다.
	//에러가 발생하는 경우, arrayList가 빈 값이 return됨.
	public static ArrayList<Board> getFemaleDelivery(){
		
		ArrayList<Board> result = new ArrayList<Board>();
		
		try {
			
			final String API_KEY="";//인증서 키를 넣어야합니다.
			String url = String.format("https://openapi.gg.go.kr/Femalesafetydelrybox?key=%s",API_KEY);
			Document res= HttpModule.requestXmlGet(url);
			NodeList sigun_nms= res.getElementsByTagName("SIGUN_NM");//시군명
			NodeList faclt_nms= res.getElementsByTagName("FACLT_NM");//택배함 명칭
			NodeList mnginst_telnos= res.getElementsByTagName("MNGINST_TELNO");//관리부서 전화번호
			NodeList refile_lotno_addrs= res.getElementsByTagName("REFINE_LOTNO_ADDR");//소재지지번주소
			NodeList refile_roadnm_addrs= res.getElementsByTagName("REFINE_ROADNM_ADDR");//소재지 도로명주소
			NodeList refine_wgs84_lats= res.getElementsByTagName("REFINE_WGS84_LAT");//위도
			NodeList refine_wgs84_logts= res.getElementsByTagName("REFINE_WGS84_LOGT");//경도
			
			for(int i=0; i<sigun_nms.getLength(); ++i) {
				//여기서 Node는 org.w3c.dom.Node
				
				Node sigun_nm =sigun_nms.item(i);
				Node faclt_nm = faclt_nms.item(i);
				Node mnginst_telno = mnginst_telnos.item(i);
				Node refile_lotno_addr = refile_lotno_addrs.item(i);
				Node refile_roadnm_addr = refile_roadnm_addrs.item(i);
				Node refine_wgs84_lat =refine_wgs84_lats.item(i);
				Node refine_wgs84_logt =refine_wgs84_logts.item(i);
				
				String siggun =sigun_nm.getFirstChild().getNodeValue();
				String faclt =faclt_nm.getFirstChild().getNodeValue();
				String mnginst =mnginst_telno.getFirstChild().getNodeValue();
				String refile_lotno =refile_lotno_addr.getFirstChild().getNodeValue();
				String refile_roadnm =refile_roadnm_addr.getFirstChild().getNodeValue();
				String refine_lat =refine_wgs84_lat.getFirstChild().getNodeValue();
				String refine_logt =refine_wgs84_logt.getFirstChild().getNodeValue();
				
				result.add(new Board(siggun, faclt, mnginst, refile_lotno, refile_roadnm, refine_lat, refine_logt));
			}
			
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
