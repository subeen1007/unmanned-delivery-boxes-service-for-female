
public class Board {
	private String siggun;//시군명
	private String faclt;//택배함명칭
	private String mnginst;//관리부서 전화번호
	private String refile_lotno;//소재지지번주소
	private String refile_roadnm;//소재지도로명주소
	private String refine_lat;//위도
	private String refine_logt;//경도
	
	
	
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
