package Backend;
public class Search {
    public static CVList listOfMatchingCVs = new CVList();
    public static void SearchMatchingCVs(String sName, String[] sExperience, String[] sSkills, String[] sEducation, String sAge)
    {
        CVList.Node temp = CVList.listOfCVs.head;
        int expCounter;
        int eduCounter;
        int sCounter;

        while(temp!=null)
        {
            if ((sName == null || sName.equalsIgnoreCase(temp.name)) && (sAge == null || sAge.equalsIgnoreCase(temp.age)))
            {
                expCounter = 0;
                eduCounter = 0;
                sCounter = 0;

                if (sExperience != null)
                {
                    for (int i = 0; i < temp.experience.length; i++)
                    {
                        for (int j = 0; j < sExperience.length; j++)
                        {
                            if (temp.experience[i].toLowerCase().equals(sExperience[j].toLowerCase()))
                            {
                                expCounter++;
                            }
                        }
                    }
                }

                if (sSkills != null)
                {
                    for (int i = 0; i < temp.skills.length; i++)
                    {
                        for (int j = 0; j < sSkills.length; j++)
                        {
                            if (temp.skills[i].toLowerCase().equals(sSkills[j].toLowerCase()))
                            {
                                sCounter++;
                            }
                        }
                    }
                }

                if (sEducation != null)
                {
                    for (int i = 0; i < temp.education.length; i++)
                    {
                        for (int j = 0; j < sEducation.length; j++)
                        {
                            if (temp.education[i].toLowerCase().equals(sEducation[j].toLowerCase()))
                            {
                                eduCounter++;
                            }
                        }
                    }
                }

                if((sEducation == null || sEducation.length==eduCounter) && (sSkills == null || sSkills.length==sCounter) && (sExperience == null || sExperience.length==expCounter)) //this will only add a cv to the matchinglist if the cv matches the requirements perfectly
                {
                    listOfMatchingCVs.InsertNode(temp.name,temp.experience,temp.skills,temp.education,temp.age);
                }
            }

            temp = temp.next;
        }
    }
}
