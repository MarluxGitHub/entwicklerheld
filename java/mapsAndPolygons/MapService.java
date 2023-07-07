package de.entwicklerheld.mapsAndPolygonsJava;

import java.util.List;

public class MapService implements Drawable {
    static PolygonProvider polygonProvider = PolygonProvider.getInstance();


    public static Double calculatePolygonArea(Point[] vertices) {
        double area = 0.0;
        int j = vertices.length - 1;
        for (int i = 0; i < vertices.length; i++) {
            area += (vertices[j].x() + vertices[i].x()) * (vertices[j].y() - vertices[i].y());
            j = i;
        }
        return Math.abs(area / 2.0);
    }


    public static void calculateAndSetLabelsForPolygons(List<String> labelNames) {
        int i = 0;
        for (Polygon polygon: polygonProvider.getPolygons()) {
            Point[] vertices = polygon.getVertices();
            int centroid_y_s = 0;
            int centroid_x = 0;
            int centroid_y = 0;
            int n = vertices.length;
            for (Point vertex: vertices) {
                centroid_y_s =  Math.max(vertex.y(), centroid_y_s);
                centroid_x += vertex.x();
                centroid_y += vertex.y();
            }

            centroid_x /= n;
            centroid_y /= n;

            Label s = new Label(new Point(0,0), labelNames.get(i));
            int x = centroid_x;
            int y = centroid_y_s;

            if(calculatePolygonArea(vertices) > 5*s.getLabelHeight()*s.getLabelWidth()) {
                x = centroid_x;
                y = centroid_y;
            }

            polygon.setLabel(new Label(new Point(x - s.getLabelWidth() / 2 , y + 2), labelNames.get(i)));
            i++;
        }
    }
}