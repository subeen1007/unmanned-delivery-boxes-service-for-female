
public class Board {
	private String siggun;//�ñ���
	private String faclt;//�ù��Ը�Ī
	private String mnginst;//�����μ� ��ȭ��ȣ
	private String refile_lotno;//�����������ּ�
	private String refile_roadnm;//���������θ��ּ�
	private String refine_lat;//����
	private String refine_logt;//�浵
	
	
	
	public Board(String siggun, String faclt, String mnginst, String refile_lotno, String refile_roadnm,
			String refine_lat, String refine_logt) {
		this.siggun = siggun;
		this.faclt = faclt;
		this.mnginst = mnginst;
		this.refile_lotno = refile_lotno;
		this.refile_roadnm = refile_roadnm;
		this.refine_lat = refine_lat;
		this.refine_logt = refine_logt;
	}
	
	public String getSiggun() {
		return siggun;
	}
	public String getFaclt() {
		return faclt;
	}
	public String getMnginst() {
		return mnginst;
	}
	public String getRefile_lotno() {
		return refile_lotno;
	}
	public String getRefile_roadnm() {
		return refile_roadnm;
	}
	public String getRefine_lat() {
		return refine_lat;
	}
	public String getRefine_logt() {
		return refine_logt;
	}
	
	
	
	
	
}
