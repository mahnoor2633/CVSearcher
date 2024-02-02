package Backend;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static Backend.CVList.listOfCVs;

public class FileRead {
    public static boolean Read(File[] cvNames, int numberOfCVs) throws IOException //this will return false if the files uploaded are in the wrong format (pdf or txt etc)
    {
        listOfCVs.head = null; //we've added this so that the list is reset each time this method is called. otherwise, if the person keeps pressing the Upload button in UploadGUI, the list will keep appending on itself

        String filePath;
        String[] education = new String[0];
        String[] skills = new String[0];
        String[] experience = new String[0];
        String name = null;
        String age = null;

        for (int i = 0; i < numberOfCVs; i++)
        {
            filePath = String.valueOf(cvNames[i]);
            try (FileInputStream fis = new FileInputStream(filePath))
            {
                XWPFDocument document = new XWPFDocument(fis);
                for (XWPFParagraph paragraph : document.getParagraphs())
                {
                    String heading = paragraph.getText().trim();
                    if (paragraph.getStyle().equals("Heading1"))
                    {
                        if (heading.equals("NAME:"))
                        {
                            name = extractTextAfterHeading(paragraph, document);
                        }
                        else if (heading.equals("AGE:"))
                        {
                            age = extractTextAfterHeading(paragraph, document);
                        }
                        else if (heading.equals("EDUCATION:"))
                        {
                            education = extractTextArrayAfterHeading(paragraph, document);
                        }
                        else if (heading.equals("SKILLS:"))
                        {
                            skills = extractTextArrayAfterHeading(paragraph, document);
                        }
                        else if (heading.equals("EXPERIENCE:"))
                        {
                            experience = extractTextArrayAfterHeading(paragraph, document);
                        }
                    }
                }
            }
            catch(NotOfficeXmlFileException ei)
            {
                return false;
            }
            listOfCVs.InsertNode(name, experience, skills, education, age);
        }

        return true;
    }
    private static String extractTextAfterHeading(XWPFParagraph heading, XWPFDocument document) //this is for getting name and age, since theyre Strings
    {
        boolean printNext = false;
        StringBuilder textBuilder = new StringBuilder();

        for (XWPFParagraph tempPara : document.getParagraphs())
        {
            if (tempPara.equals(heading))
            {
                printNext = true;
            }
            else if (printNext)
            {
                if (!tempPara.getStyle().equals("Heading1"))
                {
                    textBuilder.append(tempPara.getText());
                    textBuilder.append("\n");
                }
                else
                {
                    break;
                }
            }
        }

        return textBuilder.toString().trim();
    }

    private static String[] extractTextArrayAfterHeading(XWPFParagraph heading, XWPFDocument document) //this is for getting the rest of the attributes, since theyre String Arrays
    {
        boolean printNext = false;
        StringBuilder textBuilder = new StringBuilder();

        for (XWPFParagraph tempPara : document.getParagraphs())
        {
            if (tempPara.equals(heading))
            {
                printNext = true;
            }
            else if (printNext)
            {
                if (!tempPara.getStyle().equals("Heading1"))
                {
                    textBuilder.append(tempPara.getText());
                    textBuilder.append("\n");
                }
                else
                {
                    break;
                }
            }
        }

        return textBuilder.toString().trim().split("\n");
    }
}