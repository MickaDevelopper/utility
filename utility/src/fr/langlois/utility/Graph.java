package fr.langlois.utility;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph {

	public List<Courbe> courbes=new ArrayList<Courbe>();
	public String title="Graphique";
	
	public String axisX="X";
	public String axisY="Y";
	public Color couleurCourbe=Color.black;
	
	Graph(String title){
		this.title=title;
	}
	void addCourbe(Courbe courbe) {
		this.courbes.add(courbe);
	}
	void show() {
		
		 XYSeriesCollection dataset = new XYSeriesCollection();
			for (int i=0; i < this.courbes.size();i++) {
				dataset.addSeries(this.courbes.get(i).series);
			}
	      // Création d'un ensemble de données pour la courbe
	     
	      

	      // Création du graphique
	      JFreeChart chart = ChartFactory.createXYLineChart(
	         this.title, // Titre du graphique
	         this.axisX, // Label de l'axe X
	         this.axisY, // Label de l'axe Y
	         dataset, // Ensemble de données
	         PlotOrientation.VERTICAL, // Orientation du graphique
	         true, // Légende visible
	         true, // Tooltip visible
	         false // URL visible
	      );

	      // Personnalisation du graphique
	      XYPlot plot = (XYPlot) chart.getPlot();
	      plot.setBackgroundPaint(new Color(255, 255, 255)); // Fond blanc
	      plot.setRangeGridlinePaint(Color.BLACK); // Lignes de grille noires
	      plot.setDomainGridlinePaint(Color.BLACK); // Lignes de grille noires
	      DefaultXYItemRenderer render=new DefaultXYItemRenderer();
	      render.setSeriesPaint(0, this.couleurCourbe);
	      plot.setRenderer(render);
	      // Affichage du graphique
	      ChartFrame frame = new ChartFrame(this.title, chart);
	      frame.pack();
	      frame.setVisible(true);
	}
	void showGraphPoint() {
	    XYSeriesCollection dataset = new XYSeriesCollection();
	    for (int i=0; i < this.courbes.size(); i++) {
	        dataset.addSeries(this.courbes.get(i).series);
	    }

	    // Création du graphique avec des points non reliés
	    JFreeChart chart = ChartFactory.createScatterPlot(
	            this.title, // Titre du graphique
	            this.axisX, // Label de l'axe X
	            this.axisY, // Label de l'axe Y
	            dataset // Ensemble de données
	    );

	    // Personnalisation du graphique
	    XYPlot plot = (XYPlot) chart.getPlot();
	    plot.setBackgroundPaint(new Color(255, 255, 255)); // Fond blanc
	    plot.setRangeGridlinePaint(Color.BLACK); // Lignes de grille noires
	    plot.setDomainGridlinePaint(Color.BLACK); // Lignes de grille noires
	    DefaultXYItemRenderer render=new DefaultXYItemRenderer();
	    render.setSeriesPaint(0, this.couleurCourbe);
	    render.setSeriesShapesVisible(0, true); // Afficher les formes des points
	    render.setSeriesLinesVisible(0, false); // Ne pas relier les points
	    plot.setRenderer(render);

	    // Affichage du graphique
	    ChartFrame frame = new ChartFrame(this.title, chart);
	    frame.pack();
	    frame.setVisible(true);
	}

	
}


class Courbe{
	public XYSeries series;
	public String name;
	Courbe(String name){
		this.name=name;
		this.series=new XYSeries(name);
	}
	Courbe(String name,XYSeries series){
		this.name=name;
		this.series=series;
	}
	Courbe(String name,Matrice seriesX){
		this.name=name;
		this.series=new XYSeries(name);
		
		for (int j=0; j<seriesX.p;j++) {
			this.addPoint(seriesX.matrice[0][j], seriesX.matrice[1][j]);
		}
			
	}
	void addPoint(Double x, Double y) {
		this.series.add(x,y);
	}
	void setFunction(Function<Double,Double> f,Double a, Double b,int n) {
		Double pas=(b-a)/n;
		for (int i=0; i<=n;i++) {
			this.addPoint(a+pas*i,f.apply(a+pas*i));
		}
		
	}

}