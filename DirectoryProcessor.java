package filesprocessing;

import filesprocessing.Filtering.Filter;
import filesprocessing.Order.OrderInterface;
import filesprocessing.Parse.ParseFactory;
import filesprocessing.Section.Section;

import java.io.File;
import java.util.ArrayList;

/**
 * manager class for directory processing system
 *
 * @author rina.karnauch
 */
public class DirectoryProcessor {

    private String sourceDir;

    private String commandFile;

    private static final int SOURCE_DIR_NAME_INDEX = 0;

    private static final int COMMAND_FILE_NAME_INDEX = 1;

    private static final int ARGUMENTS_AMOUNT = 2;

    /**
     * a constructor for our directory processing system
     *
     * @param sourceDir   the source directory name
     * @param commandFile the command file name
     */
    public DirectoryProcessor(String sourceDir, String commandFile) {
        this.sourceDir = sourceDir;
        this.commandFile = commandFile;
    }

    /*
    method to run the system on the sections given
     */
    private void systemManger() throws InvalidUsageError, IOError, BadSubSectionError, BadCommandFilesError {
        Section[] systemSections = ParseFactory.parseFile(this.commandFile);
        File sourceDirFile = new File(this.sourceDir);
        if (!sourceDirFile.exists()) {
            throw new InvalidUsageError();
        } else {
            systemRunning(systemSections, sourceDirFile);
        }
    }

    /*
    method to get files in directory array
     */
    private File[] getFilesInDirectory(File sourceDir) {
        if (sourceDir == null) {
            return null;
        }
        File[] filesInDir = sourceDir.listFiles();
        if (filesInDir == null) {
            return null;
        }
        ArrayList<File> onlyFiles = new ArrayList<File>();
        for (File fileInDir : filesInDir) {
            if (fileInDir.isFile()) {
                onlyFiles.add(fileInDir);
            }
        }
        File[] files = new File[onlyFiles.size()];
        onlyFiles.toArray(files);
        return files;
    }

    /*
    get filtered array of files helper method
     */
    private File[] getFilteredFiles(File[] filesInDir, Filter filter) {
        ArrayList<File> filteredList = new ArrayList<File>();
        int i = 0;
        for (File f : filesInDir) {
            if (filter.test(f)) {
                filteredList.add(f);
                i++;
            }
        }
        File[] filteredArray = new File[i];
        return filteredList.toArray(filteredArray);
    }

    /*
    printing of order of current section
     */
    private void sectionPrinting(Section sec, File[] filesInDir) {
        if (sec.getWarnings() != null) {
            for (String warning : sec.getWarnings()) {
                System.err.println(warning);
            }
        }
        OrderInterface currentOrder = sec.getOrder();
        Filter currentFilter = sec.getFilter();
        File[] filtered = getFilteredFiles(filesInDir, currentFilter);
        filtered = currentOrder.orderFiles(filtered);
        if (filtered != null) {
            for (File file : filtered) {
                if (file != null) {
                    System.out.println(file.getName());
                }
            }
        }
    }

    /*
    system running method
     */
    private void systemRunning(Section[] systemSections, File sourceDir) {
        File[] filesInDir = getFilesInDirectory(sourceDir);
        if (filesInDir == null) {
            return;
        }
        for (Section sec : systemSections) {
            sectionPrinting(sec, filesInDir);
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length != ARGUMENTS_AMOUNT) {
                throw new InvalidUsageError();
            }
            String sourceDir = args[SOURCE_DIR_NAME_INDEX];
            String commandFile = args[COMMAND_FILE_NAME_INDEX];
            DirectoryProcessor system = new DirectoryProcessor(sourceDir, commandFile);
            system.systemManger();
        } catch (IOError | BadSubSectionError | InvalidUsageError | BadCommandFilesError error) {
            System.err.print(error.getMessage());
        }
    }
}
