package filesprocessing.Parse;

import filesprocessing.*;
import filesprocessing.IOError;
import filesprocessing.Order.*;
import filesprocessing.Filtering.*;
import filesprocessing.Section.*;

import java.io.*;
import java.util.ArrayList;

/**
 * a factory for parsing the file information
 *
 * @author rina.karnauch
 */
public class ParseFactory {

    private ParseFactory() {
        // dont want any instances of this kind
    }

    /**
     * parsing file method
     *
     * @param fileName file name to open
     * @return array of sections
     * @throws IOError              error of in or out file
     * @throws BadSubSectionError   bad sub section error
     * @throws BadCommandFilesError bad command files error
     */
    public static Section[] parseFile(String fileName) throws IOError, BadSubSectionError, BadCommandFilesError {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new IOError();
            }
            ArrayList<Section> sections = sectionsMaker(file);
            Section[] toReturn = new Section[sections.size()];
            return sections.toArray(toReturn);
        } catch (IOError ioError) {
            throw new IOError();
        } catch (BadSubSectionError badSubSectionError) {
            throw new BadSubSectionError();
        } catch (BadCommandFilesError error) {
            throw new BadCommandFilesError();
        }
    }

    /*
    parseFile helper method to make arraylist of sections and return the array of it
     */
    private static ArrayList<Section> sectionsMaker(File file) throws IOError, BadSubSectionError, BadCommandFilesError {
        ArrayList<Section> fileSections = new ArrayList<Section>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            readSections(reader, fileSections);
            return fileSections;
        } catch (IOError | IOException e) {
            throw new IOError();
        } catch (BadSubSectionError badSubSectionError) {
            throw new BadSubSectionError();
        } catch (BadCommandFilesError error) {
            throw new BadCommandFilesError();
        }
    }

    /*
    sectionsMaker helper method to read section one by one
     */
    private static void readSections(BufferedReader reader, ArrayList<Section> fileSections)
            throws IOError, BadSubSectionError, BadCommandFilesError {
        int line = ParseConstants.FIRST_LINE_INDEX;
        try {
            String inputLine = reader.readLine();
            while (inputLine != null) {
                Filter filter = null;
                OrderInterface order = null;
                String filterWarning = null;
                String errorWarning = null;
                if (inputLine.equals(ParseConstants.FILTER)) {
                    // filter section
                    // start of section of -> FILTER->anything->ORDER->
                    inputLine = reader.readLine();
                    try {
                        filter = FilterFactory.FilterMaker(inputLine, line + 1);
                    } catch (FilterWarning error) {
                        filterWarning = error.getMessage();
                        filter = FilterFactory.FilterMaker(FilterConstants.ALL, line + 1);
                    }
                    line++; // only if actually exception is not caught.
                    inputLine = reader.readLine();
                    if (inputLine == null) {
                        // no order section, bad section- end of file with FILTER.
                        throw new BadCommandFilesError();
                    } else if (!inputLine.equals(ParseConstants.ORDER)) {
                        throw new BadSubSectionError();
                    } else { // continue section after filter
                        line++;
                        inputLine = reader.readLine();
                        if (inputLine == null || inputLine.equals(ParseConstants.FILTER)) {
                            order = OrderFactory.OrderMaker(OrderConstants.ABS, line + 1);
                        } else {
                            try {
                                order = OrderFactory.OrderMaker(inputLine, line + 1);
                            } catch (OrderWarning error) {
                                errorWarning = error.getMessage();
                                order = OrderFactory.OrderMaker(OrderConstants.ABS, line + 1);
                            }
                            inputLine = reader.readLine();
                            line++; // only if actually exception is not caught.
                        }
                    }
                } else {
                    throw new BadSubSectionError();
                }
                fileSections.add(new Section(filter, order, filterWarning, errorWarning));
                line++;
            }
        } catch (IOException e) {
            throw new IOError();
        }
    }
}
