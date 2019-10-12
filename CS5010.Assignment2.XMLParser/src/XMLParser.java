/** This interface represents a simple XML parser that accepts input one character at a time. */
public interface XMLParser {
  /**
   * Accept a single character as input, and return the new parser as a result of handling this
   * character.
   *
   * <p>This function validates if the if the input stream of characters form a valid XML notation
   * or not. It follows the below conventions. 1. Each start tag has a corresponding end tag, with
   * the same tag name. 2. '<'and '>' characters are used to start and end a tag, they are reserved.
   * 3. Between a start tag and its corresponding end tag, information can be in the form of another
   * tag pair or an arbitrary string or both. 4. If tag A starts before tag B, then B must end
   * before A. Thus B is completely enclosed within A. 5. Each valid XML data has exactly one root.
   * 6. White space characters (space, tab) are allowed at any place, except tag names.
   *
   * <p>In case none of the above conditions are satisfied during the constructing of the XML, throw
   * an InvalidXMLException and exit the program to not go further.
   *
   * @param c the input character
   * @return the parser after handling the provided character
   * @throws InvalidXMLException if the input causes the XML to be invalid
   */
  XMLParser input(char c) throws InvalidXMLException;

  /**
   * Provide the output of the parser, given all the inputs it has been provided so far. The content
   * and format of this output is defined by individual implementations of XML Validator or XML
   * Logger Info class.
   *
   * @return the output of the parser as a String object
   */
  String output();
}
