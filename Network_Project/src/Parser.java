import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	//�����Ƚ� �����ù��� �����͸� ������
	//�ҷ��� �����ʹ� �������̱� ������ ������� Board ������ ������ ArrayList�� ��ȯ�Ѵ�.
	//������ �߻��ϴ� ���, arrayList�� �� ���� return��.
	public static ArrayList<Board> getFemaleDelivery(){
		
		ArrayList<Board> result = new ArrayList<Board>();
		
		try {
			
			final String API_KEY="";//������ Ű�� �־���մϴ�.
			String url = String.format("https://openapi.gg.go.kr/Femalesafetydelrybox?key=%s",API_KEY);
			Document res= HttpModule.requestXmlGet(url);
			NodeList sigun_nms= res.getElementsByTagName("SIGUN_NM");//�ñ���
			NodeList faclt_nms= res.getElementsByTagName("FACLT_NM");//�ù��� ��Ī
			NodeList mnginst_telnos= res.getElementsByTagName("MNGINST_TELNO");//�����μ� ��ȭ��ȣ
			NodeList refile_lotno_addrs= res.getElementsByTagName("REFINE_LOTNO_ADDR");//�����������ּ�
			NodeList refile_roadnm_addrs= res.getElementsByTagName("REFINE_ROADNM_ADDR");//������ ���θ��ּ�
			NodeList refine_wgs84_lats= res.getElementsByTagName("REFINE_WGS84_LAT");//����
			NodeList refine_wgs84_logts= res.getElementsByTagName("REFINE_WGS84_LOGT");//�浵
			
			for(int i=0; i<sigun_nms.getLength(); ++i) {
				//���⼭ Node�� org.w3c.dom.Node
				
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
