package filesprocessing.Filtering;

import filesprocessing.FilterWarning;

/**
 * factory for filters class, for the design pattern of creational pattern
 *
 * @author rina.karnauch
 */
public class FilterFactory {

    /*
    private factory, we don't create such instances.
     */
    private FilterFactory() {
        // no instances of this kind.
    }

    /**
     * a constructor of a filter out of a input line
     *
     * @param inputLine line of file
     * @param line      the number of line
     * @return the wanted filter
     * @throws FilterWarning exception of a bad filter format
     */
    public static Filter FilterMaker(String inputLine, int line) throws FilterWarning {
        if (inputLine == null) {
            return null;
        }
        return getFilterFactoryHelper(inputLine, line);
    }

    /*
    a helper function to send our information to the needed creation helper method
     */
    private static Filter getFilterFactoryHelper(String inputLine, int line) throws FilterWarning {
        if (inputLine == null) {
            return null;
        }
        String[] filterInformation = inputLine.split(FilterConstants.HASH_MARK, FilterConstants.SPLIT_LIMIT);
        switch (filterInformation[FilterConstants.INDEX_OF_SECTION_NAME]) {
            case (FilterConstants.GREATER_THAN): {
                return createGreaterThanFilter(filterInformation, line);
            }
            case (FilterConstants.SMALLER_THAN): {
                return createSmallerThanFiler(filterInformation, line);
            }
            case (FilterConstants.BETWEEN): {
                return createBetweenFilter(filterInformation, line);
            }
            case (FilterConstants.FILE): {
                return createFileFilter(filterInformation, line);
            }
            case (FilterConstants.CONTAINS): {
                return createContainsFilter(filterInformation, line);
            }
            case (FilterConstants.PREFIX): {
                return createPrefixFilter(filterInformation, line);
            }
            case (FilterConstants.SUFFIX): {
                return createSuffixFilter(filterInformation, line);
            }
            case (FilterConstants.WRITABLE): {
                return createWriteableFilter(filterInformation, line);
            }
            case (FilterConstants.EXECUTABLE): {
                return createExecutableFilter(filterInformation, line);
            }
            case (FilterConstants.HIDDEN): {
                return createHiddenFilter(filterInformation, line);
            }
            case (FilterConstants.ALL): {
                return createAllFilter(filterInformation, line);
            }
            default: {
                throw new FilterWarning(line);
            }
        }
    }

    /*
    a method to help find bugs in the input line before creating size filters
     */
    private static void checkSizeFilterInput(String[] inputLine, String format, int line) throws FilterWarning {
        if (format.equals(FilterConstants.GREATER_THAN) || format.equals(FilterConstants.SMALLER_THAN)) {
            if (inputLine.length > FilterConstants.NEGATE_SIZE_FILTRATION_LENGTH ||
                    inputLine.length < FilterConstants.SIZE_FILTRATION_LENGTH) {
                throw new FilterWarning(line);
            } else if (inputLine.length == FilterConstants.NEGATE_SIZE_FILTRATION_LENGTH) {
                if (!inputLine[FilterConstants.NEGATION_SIZE_INDEX].equals(FilterConstants.NOT)) {
                    throw new FilterWarning(line);
                }
            }
            double value = Double.parseDouble(inputLine[FilterConstants.VALUE_INDEX]);
            if (value < FilterConstants.MINIMAL_SIZE_VALUE) {
                throw new FilterWarning(line);
            }
        } else {
            if (inputLine.length > FilterConstants.NEGATE_RANGE_FILTRATION_LENGTH ||
                    inputLine.length < FilterConstants.RANGE_FILTRATION_LENGTH) {
                throw new FilterWarning(line);
            } else if (inputLine.length == FilterConstants.NEGATE_RANGE_FILTRATION_LENGTH) {
                if (!inputLine[FilterConstants.NEGATION_RANGE_INDEX].equals(FilterConstants.NOT)) {
                    throw new FilterWarning(line);
                }
            }
            double lowerValue = Double.parseDouble(inputLine[FilterConstants.FIRST_RANGE_VALUE_INDEX]);
            double upperValue = Double.parseDouble(inputLine[FilterConstants.SECOND_RANGE_VALUE_INDEX]);
            if (lowerValue < FilterConstants.MINIMAL_SIZE_VALUE || upperValue < FilterConstants.MINIMAL_SIZE_VALUE) {
                throw new FilterWarning(line);
            } else if (lowerValue > upperValue) {
                throw new FilterWarning(line);
            }
        }
    }

    /*
    a method to help find bugs in the input line before creating name filters
     */
    private static void checkNameFilterInput(String[] inputLine, int line) throws FilterWarning {
        if (inputLine.length < FilterConstants.MINIMAL_SUB_FILTER_LENGTH ||
                inputLine.length > FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            throw new FilterWarning(line);
        }
        if (inputLine.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            if (!inputLine[FilterConstants.NEGATION_INDEX].equals(FilterConstants.NOT)) {
                throw new FilterWarning(line);
            }
        }
    }

    /*
    a method to help find bugs in the input line before creating ability filters
     */
    private static void checkAbilityFilterInput(String[] inputLine, int line) throws FilterWarning {
        if (inputLine.length < FilterConstants.MINIMAL_SUB_FILTER_LENGTH ||
                inputLine.length > FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            throw new FilterWarning(line);
        }
        FilterConstants.logicalEnum value = getEnumOfString(inputLine[FilterConstants.LOGICAL_INDEX]);
        if (!value.isLogical()) {
            throw new FilterWarning(line);
        } else if (inputLine.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            if (!inputLine[FilterConstants.NEGATION_INDEX].equals(FilterConstants.NOT)) {
                throw new FilterWarning(line);
            }
        }
    }

    /*
    helper method for checking input
     */
    private static String getFormatCategory(String filterFormat, int line) {
        String filterCategory;
        if (filterFormat.equals(FilterConstants.GREATER_THAN) ||
                filterFormat.equals(FilterConstants.SMALLER_THAN) || filterFormat.equals(FilterConstants.BETWEEN)) {
            filterCategory = FilterConstants.SIZE;
        } else if (filterFormat.equals(FilterConstants.PREFIX) ||
                filterFormat.equals(FilterConstants.SUFFIX) ||
                filterFormat.equals(FilterConstants.CONTAINS) || filterFormat.equals(FilterConstants.FILE)) {
            filterCategory = FilterConstants.NAME;
        } else if (filterFormat.equals(FilterConstants.HIDDEN) ||
                filterFormat.equals(FilterConstants.EXECUTABLE) ||
                filterFormat.equals(FilterConstants.WRITABLE)) {
            filterCategory = FilterConstants.ABILITY;
        } else if (filterFormat.equals(FilterConstants.ALL)) {
            filterCategory = FilterConstants.ALL;
        } else {
            throw new FilterWarning(line);
        }
        return filterCategory;
    }

    /*
    a method to help us go to the needed creating helper method, according to the wanted kind of filter
     */
    private static void checkInputValidation(String[] inputLine, String filterFormat, int line) throws FilterWarning {
        String filterCategory = getFormatCategory(filterFormat, line);
        switch (filterCategory) {
            case (FilterConstants.SIZE): {
                try {
                    checkSizeFilterInput(inputLine, filterFormat, line);
                    break;
                } catch (FilterWarning e) {
                    throw new FilterWarning(line);
                }

            }
            case (FilterConstants.NAME): {
                try {
                    checkNameFilterInput(inputLine, line);
                    break;
                } catch (FilterWarning e) {
                    throw new FilterWarning(line);
                }
            }
            case (FilterConstants.ABILITY):
            case (FilterConstants.ALL): {
                try {
                    checkAbilityFilterInput(inputLine, line);
                    break;
                } catch (FilterWarning e) {
                    throw new FilterWarning(line);
                }
            }
        }
    }


    /*
    default filter
     */
    private static Filter createAllFilter(String[] fileInformation, int line) throws FilterWarning {
        if (fileInformation.length < FilterConstants.DEFAULT_MINIMAL_LENGTH || fileInformation.length >
                FilterConstants.DEFAULT_MAXIMAL_LENGTH) {
            throw new FilterWarning(line);
        } else {
            if (fileInformation.length == FilterConstants.DEFAULT_MAXIMAL_LENGTH) {
                if (fileInformation[FilterConstants.LOGICAL_INDEX].equals(FilterConstants.NOT)) {
                    return new NegateFilterDecorator(new AllFilesFilter());
                } else {
                    throw new FilterWarning(line);
                }
            } else {
                return new AllFilesFilter();
            }
        }

    }

    /*
    create method for greater than, a size filter
     */
    private static Filter createGreaterThanFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.GREATER_THAN, line);
        double value = Double.parseDouble(fileInformation[FilterConstants.VALUE_INDEX]);
        Filter wantedFilter = new GreaterThanFilter(value);
        if (fileInformation.length == FilterConstants.NEGATE_SIZE_FILTRATION_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for smaller than, a size filter
     */
    private static Filter createSmallerThanFiler(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.SMALLER_THAN, line);
        double value = Double.parseDouble(fileInformation[FilterConstants.VALUE_INDEX]);
        Filter wantedFilter = new SmallerThanFilter(value);
        if (fileInformation.length == FilterConstants.NEGATE_SIZE_FILTRATION_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for between two values, a size filter
     */
    private static Filter createBetweenFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.BETWEEN, line);
        double lowerValue = Double.parseDouble(fileInformation[FilterConstants.FIRST_RANGE_VALUE_INDEX]);
        double upperValue = Double.parseDouble(fileInformation[FilterConstants.SECOND_RANGE_VALUE_INDEX]);
        Filter wantedFilter = new BetweenSizesFilter(lowerValue, upperValue);
        if (fileInformation.length == FilterConstants.NEGATE_RANGE_FILTRATION_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for file name equality, a name filter
     */
    private static Filter createFileFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.FILE, line);
        String fileValue = fileInformation[FilterConstants.VALUE_INDEX];
        Filter wantedFilter = new FileNameEqualFilter(fileValue);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for contained name file, a name filter
     */
    private static Filter createContainsFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.CONTAINS, line);
        String fileValue = fileInformation[FilterConstants.VALUE_INDEX];
        if (fileValue.equals(FilterConstants.NOT) &&
                fileInformation.length == FilterConstants.MINIMAL_SUB_FILTER_LENGTH) {
            fileValue = FilterConstants.EMPTY_STRING;
        }
        Filter wantedFilter = new FileNameContainedFilter(fileValue);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for prefix of a file name, a name filter
     */
    private static Filter createPrefixFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.PREFIX, line);
        String fileValue = fileInformation[FilterConstants.VALUE_INDEX];
        Filter wantedFilter = new FileNamePrefixedFilter(fileValue);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    create method for suffix of a file name, a name filter
    */
    private static Filter createSuffixFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.SUFFIX, line);
        String fileValue = fileInformation[FilterConstants.VALUE_INDEX];
        Filter wantedFilter = new FileNameSuffixedFilter(fileValue);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
    helper method to create an enum
     */
    private static FilterConstants.logicalEnum getEnumOfString(String str) {
        if (str.equals(FilterConstants.YES)) {
            return FilterConstants.logicalEnum.YES;
        } else if (str.equals(FilterConstants.NO)) {
            return FilterConstants.logicalEnum.NO;
        }
        return FilterConstants.logicalEnum.NONE;
    }

    /*
    create method for writable check of a file ability, an ability filter
     */
    private static Filter createWriteableFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.WRITABLE, line);
        FilterConstants.logicalEnum logical = getEnumOfString(fileInformation[FilterConstants.LOGICAL_INDEX]);
        Filter wantedFilter = new WriteableFilter(logical);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
   create method for executable check of a file ability, an ability filter
    */
    private static Filter createExecutableFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.EXECUTABLE, line);
        FilterConstants.logicalEnum logical = getEnumOfString(fileInformation[FilterConstants.LOGICAL_INDEX]);
        Filter wantedFilter = new ExecutableFilter(logical);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

    /*
   create method for hidden check of a file ability, an ability filter
    */
    private static Filter createHiddenFilter(String[] fileInformation, int line) throws FilterWarning {
        checkInputValidation(fileInformation, FilterConstants.HIDDEN, line);
        FilterConstants.logicalEnum logical = getEnumOfString(fileInformation[FilterConstants.LOGICAL_INDEX]);
        Filter wantedFilter = new HiddenFilter(logical);
        if (fileInformation.length == FilterConstants.MAXIMAL_SUB_FILTER_LENGTH) {
            wantedFilter = new NegateFilterDecorator(wantedFilter);
        }
        return wantedFilter;
    }

}
