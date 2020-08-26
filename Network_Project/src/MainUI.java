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


// UI �� �����ϱ� ����  JFrame �� ��ӹ޴� Ŭ������ �����.
public class MainUI extends JFrame {
	
	private BoardTableModel dataModel = new BoardTableModel();
	
	//�˻� �޺��ڽ�
	String[] comboName = { "  ALL  ", "  �ñ���  ", "  �ù��Ը�Ī  "};
	JComboBox combo = new JComboBox(comboName);
	
	//�˻� �Է¶�
	JTextField jtf = new JTextField(20);
	
	public MainUI() {
		////////
	
		
		//�˻� �޺��ڽ� ��ġ, ������
		combo.setBounds(560,20,80,30);
		
		//�˻� �Է¶� ��ġ, ������
		jtf.setBounds(650,20,140,30);
        
		//�˻���ư
        JButton buttonSearch = new JButton("�˻�");
        buttonSearch.setBounds(800, 20, 80, 30);
        
        //�˻���ư�� ���� �̺�Ʈ
        buttonSearch.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		buttonSearch.setEnabled(false);
        		
        		new Thread() {
					@Override
					public void run() {
						// ��ư ��Ȱ��ȭ 
						buttonSearch.setEnabled(false);
						
						// �� ������ �� ������ �����ϴ� �Լ��� �ҷ��´�.
						loadSearchData();
						
						// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
						buttonSearch.setEnabled(true);
					}
				}.start();
        	}
        });
        ////////////////////////////
		// UI �� ���� ������
		
		// ������ ����
		setSize(950, 370);
		// ���̾ƿ��� ���� ��ǥ�� �׸� �� �ֵ��� �ϱ� ���� ���̾ƿ� ���� ����
		setLayout(null);
		// Ÿ��Ʋ ����
		setTitle("20163054_Ȳ����");
		
		// ���� ��ư ������ ���α׷� ���� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// ������ ���� �Ұ�
		setResizable(false);
		
		// ȭ�� ����� ��ġ ����
		setLocationRelativeTo(null);
		
		// ��ư ��ġ ����
		JButton buttonRefresh = new JButton("�����Ƚ� �����ù���");
		// 20, 20 ��ġ�� 200, 40 �������� ��ư
		buttonRefresh.setBounds(20, 20, 200, 40);
		// ��ư �̺�Ʈ ����
		buttonRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ͳ� ������ �ʿ��ϱ� ������ �������� ������ ���� �����ϱ� ���� ��ư Ȱ��ȭ�� ��
				buttonRefresh.setEnabled(false);
				
				// Thread �� ���, ��ư ���� ���� ����
				// ������ handler �� ���� ��ƾ�� �̿��Ͽ� main thread �� message �� ���� ui �� refresh �ϴ� �۾��� �ϴ� ���� ����
				// ���⼭�� ������ �ذ� �ϱ� ���� �׳� ��ü ������ thread �� ����. 
				// �ȵ���̵尰�� �÷��������� �̷��� �ϸ� ������ �߻���.
				new Thread() {
					@Override
					public void run() {
						// ��ư ��Ȱ��ȭ 
						buttonRefresh.setEnabled(false);
						
						// �� ������ �� ������ �����ϴ� �Լ��� �ҷ��´�.
						loadWebData();
						
						// ��� ������ ���� �ڿ� ��ư�� �ٽ� Ȱ��ȭ ������.
						buttonRefresh.setEnabled(true);
					}
				}.start();
				
			}
		});
		
		// JTable ����, ������ tableModel �� ���� �����Ѵ�.
		// [PPT ���� �����ؾ� �ϴ� �κ�] model �κ��� ���� �ۼ��ϰ�, �����ϸ� ���� ������!
		JTable dataTable = new JTable(dataModel);
		// ũ�� ���� �� �Ѱ��� ���� �����ϵ��� ����
		dataTable.setRowHeight(25);
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// scroll �ֱ�
		JScrollPane scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds(20, 60, 860, 250);
		
		
		// ��ư �� ����Ʈ�� UI �� �ֱ�
		add(buttonRefresh);
		add(scrollPane);
		add(combo);
		add(jtf);
		add(buttonSearch);
		
		
	}
	
	// UI ���� �Լ�
	public void showUI() {
		setVisible(true);
	}
	
	// [PPT ���� �ۼ��� �κ�]
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
		
		if (fieldName.trim().equals("ALL")) {// ��ü�˻�
			if (!write.trim().equals("")) {//�Է�������
				dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getSiggun().contains(write) || boardData.get(i).getFaclt().contains(write) || boardData.get(i).getMnginst().contains(write)){ 
        				dataModel.addData(boardData.get(i));	//�ñ���, �ù��Ը�Ī, �����μ���ȭ��ȣ�� �Է°�������
        			}else if(boardData.get(i).getRefile_lotno().contains(write) || boardData.get(i).getRefile_roadnm().contains(write)) {
        				dataModel.addData(boardData.get(i));	//�����������ּ�, ���������θ��ּҿ� �Է°� ������
        			}
        		
        		}
			}            
        } else if(fieldName.trim().equals("�ñ���")) { //�ñ���˻�
        	if (!write.trim().equals("")) {//�Է�������
        		dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getSiggun().contains(write))
        				dataModel.addData(boardData.get(i));
        		}
        	}
        }else if(fieldName.trim().equals("�ù��Ը�Ī")){ //�ù��Ը�Ī �˻�
        	if (!write.trim().equals("")) {//�Է�������
        		dataModel.clearData();
        		for(int i=0; i<boardData.size(); ++i) {
        			if(boardData.get(i).getFaclt().contains(write))
        				dataModel.addData(boardData.get(i));
        		}
        	}
        }
		
		
		
	}
}
