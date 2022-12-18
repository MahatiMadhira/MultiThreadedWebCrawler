package webCrawler;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Crawler_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame frame1;
    JLabel l0, l1, l2;
    JButton b1;
    Database_URL temp;
    String ids;
    static JTable table;
    String[] columnNames = {"Seq", "URL", "Parent URL"};
    String from;
    @SuppressWarnings("serial")
	Crawler_GUI(Database_URL a) {
    	
        temp = a;
        temp.connectCreate();
        ResultSet res = temp.getResult();
        
        int rowcount = 0;
        try {
           
            while (res.next()) {
                 rowcount++;
            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        String columns[] = { "id", "name", "purl" };
        
        System.out.println("Record Size: " + rowcount);
        String data[][] = new String[rowcount][3];
        res = temp.getResult();
      
        int i = 0;
       
			try {
				 while (res.next()  && (i<rowcount)) {
			        	int id;
				id = res.getInt( "ID" );
		        String name = res.getString( "URL" );
		        String purl = res.getString( "PURL" );
	          data[i][0] = id + "";
	          data[i][1] = name;
	          data[i][2] = purl;
	          i++;
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}

        System.out.println(data[0][1]);
        
        
      
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       // table.getColumnModel().getColumn(0).setPreferredWidth(100);
       // table.getColumnModel().getColumn(1).setPreferredWidth(250);
       // table.getColumnModel().getColumn(2).setPreferredWidth(200);
       // table.setShowGrid(true);
      //  table.setShowVerticalLines(true);
       
        final DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        table = new JTable(model) {
          @Override
          public boolean isCellEditable(int row, int column) {
            return false;
          }
        };
        table.setDefaultRenderer(Object.class,render);
        //table.setSize(1000, 1000);
        table.setRowMargin(15);
        //table.setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
        table.setBounds(50, 1000,1000, 1000);
        table.setRowHeight(50);
        table.setSelectionBackground(new Color(227, 227, 227));
        //table.setFillsViewportHeight(true);
        JScrollPane pane = new JScrollPane(table);
        pane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JFrame f = new JFrame("Populate JTable from Database");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(1500, 1250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        
        temp.connectClose();

    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
        	showTableData();
        }
    }
    public void showTableData() {
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
      
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            
            ResultSet rs = temp.getResult();
   
            if (rs.next()) {
    	        int id = rs.getInt( "ID" );
    	        String name = rs.getString( "URL" );
    	        String purl = rs.getString( "PURL" );
    	        
    	        System.out.println( "Result: ID = " + id + ", URL = " + name + " PURL = "+ purl );
                model.addRow(new Object[]{id, name, purl});

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }

}
