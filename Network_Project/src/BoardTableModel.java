import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BoardTableModel extends AbstractTableModel{
	private ArrayList<Board> boardList = new ArrayList<Board>();
	
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@Override
	public int getRowCount() {
		return boardList.size();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Board board = boardList.get(row);
		
		if(col==0) {
			return board.getSiggun();
		}else if(col ==1) {
			return board.getFaclt();
		}else if(col ==2) {
			return board.getMnginst();
		}else if(col ==3) {
			return board.getRefile_lotno();
		}else if(col ==4){
			return board.getRefile_roadnm();
		}else if(col ==5) {
			return board.getRefine_lat();
		}
		else {
			return board.getRefine_logt();
		}
	}
	
	private final String columns[] = {"�ñ���","�ù��� ��Ī","�����μ� ��ȭ��ȣ","�����������ּ�","������ ���θ��ּ�","����","�浵"};
	
	@Override
	public String getColumnName(int col) {
		return columns[col];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void addData(Board data) {
		boardList.add(data);
		
		fireTableDataChanged();
	}
	
	public void clearData() {
		boardList.clear();
		
		fireTableDataChanged();
	}
}
