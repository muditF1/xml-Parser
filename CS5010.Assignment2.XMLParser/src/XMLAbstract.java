import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract class for XML Validator and XML Logger which contains the functionality of 1.
 * Validating an XML while being entered 2. Constructing an output for both XML Validator and XML
 * Logger classes but which can be called in different way for each class pertaining to individual
 * classes's functionality.
 */
abstract class XMLAbstract implements XMLParser {
  private String inputString = "";
  private boolean alreadyComplete = false;
  private boolean openTag = false;
  private String character = "";
  private String endingTag = "";
  private String checkingTag = "";
  private int checkingTagIndex = 0;
  private String tempTag = "";
  private List<String> tagList = new ArrayList<String>();
  private List<String> outputDataList = new ArrayList<String>();

  @Override
  public XMLParser input(char c) throws InvalidXMLException {
    inputString = inputString + c;
    if (isValid(inputString, c)) {
      return this;
    } else {
      throw new InvalidXMLException("Unformed XML syntax");
    }
  }

  /**
   * This function validates the constraints of naming/defining the tags. A tag name can contain
   * only characters 'a-z', 'A-Z', '0-9', :, _, and -. However, a tag name cannot start with a
   * number or -.
   *
   * @param tempTag Tag that is being constructed.
   * @return if the tag is valid or not
   */
  private boolean tempTagValidator(String tempTag) {
    if (!Character.isDigit(tempTag.charAt(0)) && tempTag.charAt(0) != '-') {
      return tempTag.matches("^[a-zA-Z0-9:_-]*$");
    }
    return false;
  }

  /**
   * This function validates if the if the input stream of characters form a valid XML notation or
   * not. It follows the below conventions. 1. Each start tag has a corresponding end tag, with the
   * same tag name. 2. '<'and '>' characters are used to start and end a tag, they are reserved. 3.
   * Between a start tag and its corresponding end tag, information can be in the form of (a)
   * another tag pair or (b) an arbitrary string or both. 4. If tag A starts before tag B, then B
   * must end before A. Thus B is completely enclosed within A. 5. Each valid XML data has exactly
   * one root. 6. White space characters (space, tab) are allowed at any place, except tag names.
   *
   * @param str string which contains stream of previous valid characters from the input.
   * @param c current incoming characters.
   * @return
   */
  private Boolean isValid(String str, char c) {
    // variable to avoid multiple root elements.
    if (alreadyComplete) {
      return false;
    }

    // Now this condition to check if this is start of a tag.
    else if (c == '<') {
      if (openTag) {
        return false;
      }
      openTag = true;
      if (!character.isEmpty()) {
        // Add the List to display output in XMLInfoLogger class style to output function call.
        outputDataList.add("Characters:" + character);
      }
      // resetting the character
      character = "";
    }

    // indicates if the open tag is not active yet
    else if (!openTag) {
      if (str.length() == 1 || c == '>') {
        return false;
      }
      // continue to append the characters
      character = character + c;
    } else {
      // until a closing operator is not activiated
      if (c != '>') {
        // Check if the ending tag has started
        if (c == '/') {
          return true;
        }
        // Check if the 2'nd character to the left is the closing slash to determine the sanctity of
        // the closing tag.
        else if (str.charAt(str.length() - 2) == '/') {
          endingTag = endingTag + c;
          // take last element of the tagList and first character of it and match
          checkingTag = tagList.get(tagList.size() - 1);
          if (checkingTag.charAt(checkingTagIndex) == c) {
            checkingTagIndex++;
          } else {
            return false;
          }
        }
        // iterate over the checking tag to determine if it matches with the input char
        else if (!checkingTag.isEmpty()) {
          if (checkingTagIndex < checkingTag.length()
              && checkingTag.charAt(checkingTagIndex) == c) {
            endingTag = endingTag + c;
            checkingTagIndex++;
          } else {
            return false;
          }
        }
        // keep adding the char to the temporary tag and checking if the tag is valid or not.
        else {
          tempTag = tempTag + c;
          if (!tempTagValidator(tempTag)) {
            return false;
          }
        }
      }
      // check if the checking tag is not empty and represents a closing tag.
      else if (c == '>' && !checkingTag.isEmpty()) {
        if (!checkingTag.equals(endingTag)) {
          return false;
        }
        // Pop up the tag list with the tag names when their closing starts to happen.
        tagList.remove(tagList.size() - 1);
        outputDataList.add("Ended:" + endingTag);
        if (tagList.isEmpty()) {
          alreadyComplete = true;
        }
        checkingTagIndex = 0;
        openTag = false;
        endingTag = "";
        checkingTag = "";
      }

      // Block to keep on adding the temp tag to the master list of taglist and adding it in the
      // list to display the output.
      else {
        tagList.add(tempTag);
        outputDataList.add("Started:" + tempTag);
        openTag = false;
        tempTag = "";
      }
    }
    return true;
  }

  // Making package private
  String getInputString() {
    return inputString;
  }

  // Making package private
  List<String> getOutputDataList() {
    return outputDataList;
  }

  // Making package private
  boolean getValid() {
    return alreadyComplete;
  }
}
