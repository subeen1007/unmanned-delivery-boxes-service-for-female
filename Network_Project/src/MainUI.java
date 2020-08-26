import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


// UI 를 설정하기 위해  JFrame 을 상속받는 클래스로 만든다.
public class MainUI extends JFrame {
	
	private BoardTableModel dataModel = new BoardTableModel();
	
	//검색 콤보박스
	String[] comboName = { "  ALL  ", "  시군명  ", "  택배함명칭  "};
	JComboBox combo = new JComboBox(comboName);
	
	//검색 입력란
	JTextField jtf = new JTextField(20);
	
	public MainUI() {
		////////
	
		
		//검색 콤보박스 위치, 사이즈
		combo.setBounds(560,20,80,30);
		
		//검색 입력란 위치, 사이즈
		jtf.setBounds(650,20,140,30);
        
		//검색버튼
        JButton buttonSearch = new JButton("검색");
        buttonSearch.setBounds(800, 20, 80, 30);
        
        //검색버튼에 대한 이벤트
        buttonSearch.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		buttonSearch.setEnabled(false);
        		
        		new Thread() {
					@Override
					public void run() {
						// 버튼 비활성화 
						buttonSearch.setEnabled(false);
						
						// 웹 데이터 및 데이터 설정하는 함수를 불러온다.
						loadSearchData();
						
						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						buttonSearch.setEnabled(true);
					}
				}.start();
        	}
        });
        ////////////////////////////
		// UI 에 대한 설정들
		
		// 사이즈 설정
		setSize(950, 370);
		// 레이아웃을 절대 좌표로 그릴 수 있도록 하기 위해 레이아웃 설정 제거
		setLayout(null);
		// 타이틀 설정
		setTitle("20163054_황수빈");
		
		// 종료 버튼 누르면 프로그램 종료 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 사이즈 변경 불가
		setResizable(false);
		
		// 화면 가운데로 위치 설정
		setLocationRelativeTo(null);
		
		// 버튼 위치 설정
		JButton buttonRefresh = new JButton("여성안심 무인택배함");
		// 20, 20 위치에 200, 40 사이즈의 버튼
		buttonRefresh.setBounds(20, 20, 200, 40);
		// 버튼 이벤트 설정
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 인터넷 연결이 필요하기 때문에 연속으로 누르는 것을 방지하기 위해 버튼 활성화를 끔
				buttonRefresh.setEnabled(false);
				
				// Thread 로 덮어서, 버튼 멈춘 현상 제거
				// 원래는 handler 와 같은 루틴을 이용하여 main thread 에 message 를 날려 ui 를 refresh 하는 작업을 하는 것이 정석
				// 여기서는 간단히 해결 하기 위해 그냥 전체 영역을 thread 로 덮음. 
				// 안드로이드같은 플랫폼에서는 이렇게 하면 오류가 발생함.
				new Thread() {
					@Override
					public void run() {
						// 버튼 비활성화 
						buttonRefresh.setEnabled(false);
						
						// 웹 데이터 및 데이터 설정하는 함수를 불러온다.
						loadWebData();
						
						// 모든 동작이 끝난 뒤에 버튼을 다시 활성화 시켜줌.
						buttonRefresh.setEnabled(true);
					}
				}.start();
				
			}
		});
		
		// JTable 설정, 생성한 tableModel 과 같이 연결한다.
		// [PPT 먼저 진행해야 하는 부분] model 부분을 먼저 작성하고, 선언하면 오류 없어짐!
		JTable dataTable = new JTable(dataModel);
		// 크기 설정 및 한개만 선택 가능하도록 설정
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// scroll 넣기
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds(20, 60, 860, 250);
		
		
		// 버튼 및 리스트를 UI 에 넣기
		add(buttonRefresh);
		add(scrollPane);
		add(combo);
		add(jtf);
		add(buttonSearch);
		
		
	}
	
	// UI 띄우는 함수
	public void showUI() {
		setVisible(true);
	}
	
	// [PPT 보고 작성할 부분]
	private void loadWebData() {
		ArrayList<Board> boardData = Parser.getFemaleDelivery();
		dataModel.clearData();
		for(int i=0; i<boardData.size(); ++i) {
			dataModel.addData(boardData.get(i));
		}
	}
	
	private void loadSearchData() {
		
		ArrayList<Board> boardData = Parser.getFemaleDelivery();
		
		String fieldName = combo.getSelectedItem().toString();
		String write = jtf.getText();
		
		if (fieldName.trim().equals("ALL")) {// 전체검색
			if (!write.trim().equals("")) {//입력했을때
				dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getSiggun().contains(write) || boardData.get(i).getFaclt().contains(write) || boardData.get(i).getMnginst().contains(write)){ 
        				dataModel.addData(boardData.get(i));	//시군명, 택배함명칭, 관리부서전화번호에 입력값있을떄
        			}else if(boardData.get(i).getRefile_lotno().contains(write) || boardData.get(i).getRefile_roadnm().contains(write)) {
        				dataModel.addData(boardData.get(i));	//소재지지번주소, 소재지도로명주소에 입력값 있을떄
        			}
        		
        		}
			}            
        } else if(fieldName.trim().equals("시군명")) { //시군명검색
        	if (!write.trim().equals("")) {//입력했을때
        		dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getSiggun().contains(write))
        				dataModel.addData(boardData.get(i));
        		}
        	}
        }else if(fieldName.trim().equals("택배함명칭")){ //택배함명칭 검색
        	if (!write.trim().equals("")) {//입력했을때
        		dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getFaclt().contains(write))
        				dataModel.addData(boardData.get(i));
        		}
        	}
        }
		
		
		
	}
}
