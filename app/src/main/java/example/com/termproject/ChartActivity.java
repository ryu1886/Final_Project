package example.com.termproject;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class ChartActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout chart_area = (LinearLayout) findViewById(R.id.chart_area);

        CategorySeries series = new CategorySeries(null);
        DefaultRenderer renderer = new DefaultRenderer();

        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA,
                Color.YELLOW, Color.CYAN, Color.RED };

        series.add("Seoul", new Integer(40));
        series.add("Tokyo", new Integer(5));
        series.add("Taipei", new Integer(10));
        series.add("beijing", new Integer(25));
        series.add("Busan", new Integer(20));
        series.add("Deajeon", new Integer(50));

        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(24);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }

        GraphicalView pie_chart = ChartFactory.getPieChartView(this, series, renderer);
        chart_area.addView(pie_chart);
    }
}
