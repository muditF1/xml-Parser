/**
 * This is the XMLValidator class which validates the input character stream by stream and validates
 * if the input is right or wrong for a successful XML to form.
 */
public class XMLValidator extends XMLAbstract {

  /**
   * This method should return a single word that represents the current status of the input
   * provided. 1. If no inputs have been provided yet, the method should return "Status:Empty".If
   * the inputs provided form complete, valid XML (i.e. all tag names are valid, each start tag has
   * a corresponding end tag, tags are properly nested, root tag occurs only once) then the method
   * should return "Status:Valid". If the inputs thus far can be part of valid XML but the data is
   * not yet complete it should return "Status:Incomplete".
   *
   * @return the status of the current XML character input.
   */
  @Override
  public String output() {
    if (this.getValid()) {
      return "Status:Valid";
    } else if (this.getInputString().trim().isEmpty()) {
      return "Status:Empty";
    } else {
      return "Status:Incomplete";
    }
  }
}
