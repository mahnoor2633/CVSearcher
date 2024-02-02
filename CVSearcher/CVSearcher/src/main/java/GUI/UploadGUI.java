package GUI;

import Backend.FileRead;
import Backend.FolderReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UploadGUI extends Jframe implements ActionListener {
    JButton uploadBtn = new JButton("UPLOAD");
    boolean check;
    public UploadGUI()
    {
        Font newFont = new Font("Britannic Bold", Font.PLAIN, 20);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        // Create a panel for components
        JPanel uploadPnl = new JPanel(new GridBagLayout());
        uploadPnl.setBackground(Color.BLACK);
        uploadPnl.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel main_upload_title = new JLabel("<html><b><u>UPLOAD CVs</u></b></html>");
        main_upload_title.setFont(newFont);
        main_upload_title.setForeground(Color.WHITE);
        main_upload_title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(30, 50, 10, 50);
        uploadPnl.add(main_upload_title, gbc);

        // Open CVs Folder button
        JButton openBtn = new JButton("OPEN CVs FOLDER");
        openBtn.setFont(newFont);
        openBtn.setBackground(Color.BLACK);
        openBtn.setForeground(Color.WHITE);
        openBtn.setPreferredSize(new Dimension(250, 40));
        openBtn.setHorizontalAlignment(SwingConstants.CENTER);
        openBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        openBtn.addActionListener(this);
        gbc.insets = new Insets(30, 50, 10, 50);
        uploadPnl.add(openBtn, gbc);

        // Instructions
        JLabel instrLbl = new JLabel("<html>&#8226; Press the \"Open CVs Folder\" button.<br>&#8226; Move the CVS to the folder that pops up.<br>&#8226; Press the Upload button to upload the CVs.<br>&#8226; Return to the Home Page and press the Search button to search through the CVs.</html>");
        instrLbl.setFont(newFont);
        instrLbl.setForeground(Color.WHITE);
        instrLbl.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(30, 80, 10, 80);
        uploadPnl.add(instrLbl, gbc);

        // Upload button
        uploadBtn.setFont(newFont);
        uploadBtn.setForeground(Color.WHITE);
        uploadBtn.setBackground(Color.BLACK);
        uploadBtn.setPreferredSize(new Dimension(250, 40));
        uploadBtn.setHorizontalAlignment(SwingConstants.CENTER);
        uploadBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        uploadBtn.addActionListener(eu -> {
            try {
                uploadFiles();
            } catch (IOException ex) {
                JOptionPane.showInternalMessageDialog(this.getParent(), "FILE UPLOAD FAILED", "UPLOAD FAILED!", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.insets = new Insets(30, 50, 10, 50);
        uploadPnl.add(uploadBtn, gbc);

        // Back button
        JButton backBtn = new JButton("BACK");
        backBtn.setFont(newFont);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setPreferredSize(new Dimension(250, 40));
        backBtn.setHorizontalAlignment(SwingConstants.CENTER);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        backBtn.addActionListener(eu -> {
            this.setVisible(false);
            new HomeFrameGUI();
        });
        gbc.insets = new Insets(30, 50, 10, 50);
        uploadPnl.add(backBtn, gbc);

        this.add(uploadPnl, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            openFileChooser();
            uploadBtn.setEnabled(true);
        }
        catch (IOException ex)
        {
            JOptionPane.showInternalMessageDialog(this.getParent(), "FOLDER NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openFileChooser() throws IOException
    {
            String folderName = "CVs";
            String folderPath = FolderReader.getPath(folderName);

            try
            {
                File folderToOpen = new File(folderPath);
                Desktop.getDesktop().open(folderToOpen);
            }
            catch (IOException ex)
            {
                JOptionPane.showInternalMessageDialog(this.getParent(), "FOLDER NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            catch (IllegalArgumentException ex)
            {
                JOptionPane.showInternalMessageDialog(this.getParent(), "FOLDER NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }

    private void uploadFiles() throws IOException
    {
        if (FolderReader.getCVNames() == null || FolderReader.getNumberOfCVs() == 0)
        {
            uploadBtn.setEnabled(false);
            JOptionPane.showInternalMessageDialog(this.getParent(), "FOLDER IS EMPTY OR DOESN'T EXIST", "FOLDER EMPTY OR DOESN'T EXIST", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            uploadBtn.setEnabled(true);
            check = FileRead.Read(FolderReader.getCVNames(), FolderReader.getNumberOfCVs()); //will return false if the file format is wrong

            if (check)
            {
                JOptionPane.showInternalMessageDialog(this.getParent(), "FILES UPLOADED SUCCESSFULLY", "UPLOAD SUCCESSFUL!", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
                new HomeFrameGUI();
            }
            else
            {
                JOptionPane.showInternalMessageDialog(this.getParent(), "FILE UPLOAD FAILED", "UPLOAD FAILED! ENSURE FILE FORMAT IS (.docx)", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
