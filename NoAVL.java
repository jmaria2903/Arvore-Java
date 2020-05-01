/* Define um nó de uma árvore AVL */

public class NoAVL
{
	private Object dado;	//Dado do nó
	private int fb;			//Fator de balanceamento
	private NoAVL pai;		//Pai do nó
	private NoAVL esq;		//Filho Esquerdo
	private NoAVL dir;		//Filho Direito
	
	
	public NoAVL(Object _dado, int _fb, NoAVL _pai, NoAVL _esq, NoAVL _dir)	{
		dado = _dado;
		fb = _fb;
		pai = _pai;
		esq = _esq;
		dir = _dir;
	}
	
	public NoAVL() {
		this("",0,null,null,null);
	}
	
	public NoAVL(Object _dado) {
		this(_dado,0,null,null,null);
	}
	
	public Object getDado() {
		return dado;
	}
	
	public void setDado(Object _dado) {
		dado = _dado;
	}
	
	public int getFb() {
		return fb;
	}
	
	public void setFb(int _fb) {
		fb = _fb;
	}
	
	public NoAVL getPai() {
		return pai;
	}
	
	public void setPai(NoAVL _pai) {
		pai = _pai;
	}
	
	public NoAVL getEsq() {
		return esq;
	}
	
	public void setEsq(NoAVL _esq) {
		esq = _esq;
	}
	
	public NoAVL getDir() {
		return dir;
	}
	
	public void setDir(NoAVL _dir) {
		dir = _dir;
	}
	
}