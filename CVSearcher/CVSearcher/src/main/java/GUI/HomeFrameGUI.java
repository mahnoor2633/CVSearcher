package GUI;

import Backend.FolderReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrameGUI extends JFrame implements ActionListener {
    JButton filterMenuButton = new JButton("FILTER CVs");
    public HomeFrameGUI()
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Panel for the main content
        JPanel homePnl = new JPanel(new GridBagLayout());
        homePnl.setBackground(Color.BLACK);
        homePnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label
        JLabel main_home_title = new JLabel("<html><b><u>UPLOAD CVs</u></b></html>");
        main_home_title.setFont(newFont);
        main_home_title.setForeground(Color.WHITE);
        main_home_title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(30, 50, 10, 50);
        homePnl.add(main_home_title,gbc);

        // Add Files Button
        JButton add_files_Btn = new JButton("ADD FILES");
        add_files_Btn.setFont(newFont);
        add_files_Btn.setBackground(Color.BLACK);
        add_files_Btn.setForeground(Color.WHITE);
        add_files_Btn.setPreferredSize(new Dimension(250, 40));
        add_files_Btn.setHorizontalAlignment(SwingConstants.CENTER);
        add_files_Btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        add_files_Btn.addActionListener(eu -> {
            this.setVisible(false);
            new UploadGUI();
        });
        gbc.insets = new Insets(30, 50, 10, 50);
        homePnl.add(add_files_Btn,gbc);

        // Search CV Button
        filterMenuButton.setFont(newFont);
        filterMenuButton.setForeground(Color.WHITE);
        filterMenuButton.setBackground(Color.BLACK);
        filterMenuButton.setPreferredSize(new Dimension(250, 40));
        filterMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        filterMenuButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        filterMenuButton.addActionListener(this);
        gbc.insets = new Insets(30, 50, 10, 50);
        homePnl.add(filterMenuButton,gbc);

        this.add(homePnl, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent echck)
    {
        if (echck.getSource() == filterMenuButton)
        {
            if (FolderReader.getCVNames() == null || FolderReader.getNumberOfCVs() == 0) //if the folder doesnt exist or no cvs have been added, the button to open the filter menu will be disabled
            {
                filterMenuButton.setEnabled(false);
                JOptionPane.showInternalMessageDialog(this.getParent(), "FOLDER IS EMPTY OR DOESN'T EXIST. PLEASE CREATE FOLDER OR ADD CVS TO IT IF IT DOESN'T EXIST.", "FOLDER EMPTY OR DOESN'T EXIST", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                filterMenuButton.setEnabled(true);
                new FilterSearchGUI();
                this.setVisible(false);
            }
        }
    }
}
