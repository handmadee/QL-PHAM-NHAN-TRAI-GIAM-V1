package com.mycompany.ql.pham.nhan.trai.giam.v1.src.displays;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.components.InfoPanel;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonerService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.PrisonService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.services.RegistrationService;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.models.PrisonSummary;
import com.mycompany.ql.pham.nhan.trai.giam.v1.src.utils.RefreshablePanel;



import java.awt.Color;
import java.util.List;

public class DashBoardGUI extends JPanel implements RefreshablePanel {
    private PrisonerService prisonerService;
    private PrisonService prisonService;
    private RegistrationService registrationService;
    
    private JPanel jChart;
    private JLabel txtHouse, txtPrison, txtPrison2;

    public DashBoardGUI() {
        setLayout(new BorderLayout());
        prisonService = new PrisonService();
        prisonerService = new PrisonerService();
        registrationService = new RegistrationService();
        setPreferredSize(new Dimension(1350, 800)); 
        initComponents(); 
        createBarChart();
    }
    
 @Override
public void refreshData() {
    txtHouse.setText("Tổng số phạm nhân: " + prisonerService.getTotalPrisoners());
    txtPrison.setText("Số lượng trại giam: " + prisonService.getTotalPrisons());
    txtPrison2.setText("Số lượng đăng ký: " + registrationService.getTotalRegistrations());

    // Cập nhật biểu đồ
    if (jChart != null) {
        remove(jChart);  
    }
    createBarChart();  
    revalidate();
    repaint();    
}


    private void createBarChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ về số phạm nhân phạm tội", 
                "Trại giam", 
                "Số phạm nhân", 
                dataset, 
                PlotOrientation.VERTICAL, 
                true, 
                true, 
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1100, 500));
        jChart.setLayout(new BorderLayout());
        jChart.add(chartPanel, BorderLayout.CENTER);
        jChart.validate();
    }

private CategoryDataset createDataset() {
    List<PrisonSummary> prisonSummaries = prisonService.getAllPrisonSummaries();
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    if (prisonSummaries == null || prisonSummaries.isEmpty()) {
        System.out.println("Không có trại giam nào trong hệ thống.");
        return dataset; 
    }
    for (PrisonSummary prison : prisonSummaries) {
        String prisonName = prison.getPrisonName();
        int prisonerCount = prison.getPrisonerCount();
        System.out.println("Tên trại: " + prisonName + ", Số lượng phạm nhân: " + prisonerCount);
        dataset.addValue(prisonerCount, prisonName, "Phạm nhân");
    }

    return dataset;
}


private void initComponents() {
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    jChart = new JPanel();
    int totalPrison = prisonService.getTotalPrisons();
    long totalPrisoner = prisonerService.getTotalPrisoners();
    int totalResgiter = registrationService.getTotalRegistrations();
    

    // Sử dụng InfoPanel cho các thông tin khác nhau
    InfoPanel panelTrạiGiam = new InfoPanel("TỔNG SỐ TRẠI GIAM", String.valueOf(totalPrison), new Color(97, 173, 120));
    InfoPanel panelPhạmNhân = new InfoPanel("TỔNG SỐ PHẠM NHÂN", String.valueOf(totalPrisoner), new Color(97, 173, 120));
    InfoPanel panelĐăngKý = new InfoPanel("TỔNG SỐ ĐƠN ĐĂNG KÝ", String.valueOf(totalResgiter), new Color(97, 173, 120));

    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));
    jPanel3.add(panelTrạiGiam);
    jPanel3.add(panelPhạmNhân);
    jPanel3.add(panelĐăngKý);

    jPanel2.add(jPanel3);

    jPanel1.setLayout(new BorderLayout());
    jPanel1.add(jPanel2, BorderLayout.NORTH);
    jPanel1.add(jChart, BorderLayout.CENTER);

    this.add(jPanel1, BorderLayout.CENTER);
}

}
