package GUI;

import Backend.CVList;
import Backend.Search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PrintCVsGUI extends Jframe implements ActionListener
{
    CVList.Node temp = Search.listOfMatchingCVs.head;
    JButton print_screen_back_Btn = new JButton("DONE");

    public PrintCVsGUI()
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Create a panel for displaying CVs
        JPanel main_Print_Pnl = new JPanel();
        main_Print_Pnl.setBackground(Color.BLACK);
        main_Print_Pnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        main_Print_Pnl.setLayout(new BoxLayout(main_Print_Pnl, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(main_Print_Pnl);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Loop through the CV list and add information panels
        while (temp != null)
        {
            JPanel prntPnl = new JPanel();
            prntPnl.setBackground(Color.gray);
            prntPnl.setLayout(new GridLayout(5, 1)); // 5 rows, 1 column
            prntPnl.setBorder(BorderFactory.createLineBorder(Color.lightGray, 5));
            prntPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
            prntPnl.setMinimumSize(new Dimension(500, 200)); // Adjust dimensions as needed

            addLabelAndTextArea(("NAME: " + temp.name), prntPnl);
            addLabelAndTextArea(("EXPERIENCE: " + Arrays.toString(temp.experience)), prntPnl);
            addLabelAndTextArea(("SKILLS: " + Arrays.toString(temp.skills)), prntPnl);
            addLabelAndTextArea(("EDUCATION: " + Arrays.toString(temp.education)), prntPnl);
            addLabelAndTextArea(("AGE: " + temp.age), prntPnl);

            // Add the panel to the main panel
            main_Print_Pnl.add(Box.createVerticalStrut(20));
            main_Print_Pnl.add(prntPnl);

            // Move to the next CV node
            temp = temp.next;
        }

        // BACK BUTTON
        print_screen_back_Btn.setFont(newFont);
        print_screen_back_Btn.setForeground(Color.WHITE);
        print_screen_back_Btn.setBackground(Color.BLACK);
        print_screen_back_Btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        print_screen_back_Btn.addActionListener(this);
        print_screen_back_Btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        print_screen_back_Btn.setMaximumSize(new Dimension(250, 40)); // Adjust dimensions as needed
        main_Print_Pnl.add(Box.createVerticalStrut(20));
        main_Print_Pnl.add(print_screen_back_Btn);

        this.add(scrollPane, BorderLayout.CENTER);
        pack();
        setMinimumSize(new Dimension(900, 400)); // Setting a minimum size for the frame. this will make it so that if only one CV is printed, it won't look all squished in the frame
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void addLabelAndTextArea(String labelText, JPanel panel)
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);

        JLabel label = new JLabel(labelText);
        label.setFont(newFont);
        label.setForeground(Color.WHITE);
        label.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);
        new HomeFrameGUI();
    }
}
