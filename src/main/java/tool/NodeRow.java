package tool;

import engine.MainQuiz;

/**
 * 
 * @author matteo
 *
 */
public class NodeRow {
	
	private int n;
	private int ndiv3;
	private int nmod3;
	private int rowCost;
	
	public NodeRow(int n){
		this.n = n;
		this.ndiv3 = n/MainQuiz.WIN_POINTS;
		this.nmod3 = n % MainQuiz.WIN_POINTS;
		this.rowCost = calcRowCost();
	}
	
	/**
	 * calculate cost for a single node row
	 * @return
	 */
	private int calcRowCost(){
		int rowCost = 0;
		if (this.nmod3 == 0)
			rowCost = this.ndiv3;
		else
			rowCost = this.ndiv3 + 1;
		return rowCost;
	}

	/**
	 * print a node row
	 */
	public String toString(){
		String out =   this.n + " (" + this.rowCost + ") - [" + this.nmod3 + "]";
		return out;
	}
	
	
	public int getRowCost() {
		return rowCost;
	}

	public void setRowCost(int rowCost) {
		this.rowCost = rowCost;
	}

	public int getNmod3() {
		return nmod3;
	}

	public void setNmod3(int nmod3) {
		this.nmod3 = nmod3;
	}

	public int getN() {
		return n;
	}

}
