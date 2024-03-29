package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JDialogGraphic extends JDialog implements ActionListener,WindowListener {
	
	/**
	 * Constante que representa el boton de cerrar ventana<br>
	 */
	public static final String CLOSE = "Cerrar ventana";
	/**
	 * Arreglo con los nodos que se van a dibujar<br>
	 */
	private Node[] nodes;
	/**
	 * Atributo que representa el n�mero de vertices del grafo<br>
	 */
	private int V;
	/**
	 * Atributo para obtener un n�mero random<br>
	 */
	private Random rd = new Random(System.nanoTime());
	/**
	 * Relaci�n con la ventana principal<br>
	 */
	private MainWindow main;
	
	/**
	 * Crea un JDialogGraphic<br>
	 * @param edges con la informci�n de las aristas del grafo a dibujar<br>
	 * @param main con la referencia de la ventana principal<br>
	 */
	public JDialogGraphic(int[][] edges, MainWindow main) {
		super(main);
		V = edges[0][0];
		convertNodes(edges);
		this.main = main;
		addWindowListener(this);
		init();
	}
	
	/**
	 * M�todo que inicializa las propiedades del JDialog<br>
	 */
	private void init() {
		JPanel canvas = new JPanel(new GridLayout(1,1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension((int)(MainWindow.WIDTH*1.3),(int)(MainWindow.HEIGHT*1.3)));
		setResizable(false);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)(dm.getWidth()/2)-((int)(MainWindow.WIDTH*1.3)/2)),((int)(dm.getHeight()/2))-((int)(MainWindow.HEIGHT*1.3)/2));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Gr�fica auxiliar de los sistemas de estrellas para el caso 1");
		JPanel aux = new JPanel(new BorderLayout());
		aux.setBorder(BorderFactory.createMatteBorder(10,0,10,5,MainWindow.BACKGROUND));
		aux.setPreferredSize(new Dimension((int)(MainWindow.WIDTH/3.7),MainWindow.HEIGHT));
		canvas.setBorder(BorderFactory.createMatteBorder(10,10,10,10,MainWindow.BACKGROUND));
		JButton cerrar = new JButton(CLOSE);
		cerrar.addActionListener(this);
		JLabel informacion = new JLabel();
		informacion.setText(
				"<html><center>"
				+ "*Informaci�n a tener en cuenta*<br/><br/>"
				+ " > A mayor tama�o de pantalla mayor espacio de dibujo.<br/>"
				+ " > Si tiene muchos nodos, probablemente no se distigan bien en el area de dibujo.<br/>"
				+ " > Si tiene muchas aristas, probablemente no se distigan bien en el area de dibujo.<br/>"
				+ " > Si hay aristas superpuestas una encima de otra, lo m�s seguro aunque no del todo, es que hay ciclos entre los nodos que conecta.<br/>"
				+ " > Si desea visualizar mejor la representaci�n gr�fica, cierre esta ventana y vuelva a"
				+ " abrirla. Puede usar el bot�n de abajo o dando click en la x de la parte superior.<br/>"
				+ "<br/><br/><br/><br/><br/>Aplicaci�n desarrolada por<br/>Kliver - Joe - Christian"
				+ "</center></html>"
				);
		aux.add(informacion,BorderLayout.CENTER);
		aux.add(cerrar,BorderLayout.SOUTH);
		add(aux,BorderLayout.EAST);
		add(canvas,BorderLayout.CENTER);
		setVisible(true);
		pack();
	}
	
	/**
	 * M�todo que convierte un arreglo bidimensional de aristas a un arreglo de nodos<br>
	 * @param edges con la informaci�n de las aristas<br>
	 */
	public void convertNodes(int[][] edges) {
		int x = Node.d/4;
		int y = (int)(Node.d/1.2);
		nodes = new Node[V];
		for (int i = 0; i < V; i++) {
			int xP = rd.nextInt((int)(MainWindow.WIDTH*1.15)-(int)((x+Node.d/2)*5))+x+Node.d/2,yP = rd.nextInt((int)(MainWindow.HEIGHT*1.3)-(int)((y+Node.d/2)*1.85))+y+Node.d/2;
			nodes[i] = new Node(xP+rd.nextInt(30),yP,i);
		}
		for (int i = 1; i < edges.length; i++) {
			int id1 = edges[i][0],id2 = edges[i][1];
			Join act = new Join(edges[i][2]+"",nodes[id1],nodes[id2]);
			act.setId1(id1);
			act.setId2(id2);
			nodes[id1].getJoins().add(act);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < nodes.length; i++) {
			for (Join j : nodes[i].getJoins()) {
		        j.paintLines(g,V<36);
			}
		}
		for (Node node : nodes) {
			node.paint(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(CLOSE)) {
			this.dispose();
			windowClosing(null);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		main.setVisible(true);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
