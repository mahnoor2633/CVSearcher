package GUI;

import Backend.Search;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterSearchGUI extends Jframe implements ActionListener {
    String cv_Name, cv_Age;
    String[] cv_Exp, cv_Skill, cv_Edu;
    JTextArea nameTxt;
    JTextArea expTxt;
    JTextArea skillsTxt;
    JTextArea eduTxt;
    JTextArea ageTxt;

    static JButton filter_search_Btn;
    static JButton filter_screen_back_Btn;

    public FilterSearchGUI()
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Create a panel for components
        JPanel searchPnl = new JPanel(new GridBagLayout());
        searchPnl.setBackground(Color.BLACK);
        searchPnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel filter_Frame_Title = new JLabel("<html><b><u>FILTER THE UPLOADED CVs</u></b></html>");
        filter_Frame_Title.setFont(newFont);
        filter_Frame_Title.setForeground(Color.WHITE);
        filter_Frame_Title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(30, 50, 10, 50);
        searchPnl.add(filter_Frame_Title, gbc);

        //---INITIALIZING THE TEXTAREAS WITHIN THE CONSTRUCTOR SUCH THAT THE DOCUMENT LISTENER CAN BE PROPERLY ATTATCHED
        nameTxt = new JTextArea(1,15);
        expTxt = new JTextArea(1,15);
        skillsTxt = new JTextArea(1,15);
        eduTxt = new JTextArea(1,15);
        ageTxt = new JTextArea(1,15);

        // Labels and text areas
        addLabelAndTextArea("ENTER REQUIRED NAME:", nameTxt, searchPnl, gbc);
        addLabelAndTextArea("ENTER REQUIRED EXPERIENCE:", expTxt, searchPnl, gbc);
        addLabelAndTextArea("ENTER REQUIRED SKILLS:", skillsTxt, searchPnl, gbc);
        addLabelAndTextArea("ENTER REQUIRED EDUCATION:", eduTxt, searchPnl, gbc);
        addLabelAndTextArea("ENTER REQUIRED AGE:", ageTxt, searchPnl, gbc);

        // Adding document listener to each textfield. this will allow us to enable the search button only when the text fields are not empty
        addDocumentListenerToTextArea(nameTxt);
        addDocumentListenerToTextArea(expTxt);
        addDocumentListenerToTextArea(skillsTxt);
        addDocumentListenerToTextArea(eduTxt);
        addDocumentListenerToTextArea(ageTxt);

        // Search button
        filter_search_Btn = new JButton("SEARCH");
        filter_search_Btn.setFont(newFont);
        filter_search_Btn.setForeground(Color.WHITE);
        filter_search_Btn.setBackground(Color.BLACK);
        filter_search_Btn.setPreferredSize(new Dimension(250, 40));
        filter_search_Btn.setHorizontalAlignment(SwingConstants.CENTER);
        filter_search_Btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        gbc.insets = new Insets(30, 50, 10, 50);
        searchPnl.add(filter_search_Btn, gbc);
        filter_search_Btn.setEnabled(false);
        filter_search_Btn.addActionListener(this);

        // Back button
        filter_screen_back_Btn = new JButton("BACK");
        filter_screen_back_Btn.setFont(newFont);
        filter_screen_back_Btn.setForeground(Color.WHITE);
        filter_screen_back_Btn.setBackground(Color.BLACK);
        filter_screen_back_Btn.setPreferredSize(new Dimension(250, 40));
        filter_screen_back_Btn.setHorizontalAlignment(SwingConstants.CENTER);
        filter_screen_back_Btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        filter_screen_back_Btn.addActionListener(e -> {
            this.setVisible(false);
            new HomeFrameGUI();
        });
        gbc.insets = new Insets(10, 50, 30, 50);
        searchPnl.add(filter_screen_back_Btn, gbc);

        this.add(searchPnl, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addLabelAndTextArea(String labelText, JTextArea textArea, JPanel panel, GridBagConstraints gbc)
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);

        JLabel label = new JLabel(labelText);
        label.setFont(newFont);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);

        textArea.setFont(newFont);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(textArea, gbc);
    }

    private void updateButtonState() //this method allows us to lock the button if no parameters are entered in the textfields
    {
        if (nameTxt.getText().trim().isEmpty() && ageTxt.getText().trim().isEmpty() && eduTxt.getText().trim().isEmpty() && skillsTxt.getText().trim().isEmpty() && expTxt.getText().trim().isEmpty())
        {
            filter_search_Btn.setEnabled(false);
        }
        else
        {
            filter_search_Btn.setEnabled(true);
        }
    }

    private void addDocumentListenerToTextArea(JTextArea textArea) {
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent es)
    {
        if (es.getSource() == filter_search_Btn) {
            Search.listOfMatchingCVs.head = null; //resetting the templist each time the search button is pressed. otherwise, it will keep appending previous matches to the list

            if (nameTxt.getText().isEmpty()) {
                cv_Name = null;
            } else {
                cv_Name = nameTxt.getText();
            }

            if (ageTxt.getText().isEmpty()) {
                cv_Age = null;
            } else {
                cv_Age = ageTxt.getText();
            }

            if (eduTxt.getText().isEmpty()) {
                cv_Edu = null;
            } else {
                cv_Edu = eduTxt.getText().split(",");
            }

            if (expTxt.getText().isEmpty()) {
                cv_Exp = null;
            } else {
                cv_Exp = expTxt.getText().split(",");
            }

            if (skillsTxt.getText().isEmpty()) {
                cv_Skill = null;
            } else {
                cv_Skill = skillsTxt.getText().split(",");
            }
            Search.SearchMatchingCVs(cv_Name, cv_Exp, cv_Skill, cv_Edu, cv_Age);

            if (Search.listOfMatchingCVs.head == null)
            {
                JOptionPane.showInternalMessageDialog(this.getParent(), "NO MATCHES FOUND", "NO CV MATCHED", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                this.setVisible(false);
                new PrintCVsGUI();
            }
        }
    }
}
