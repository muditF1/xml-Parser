/**
 * This is the XMLInfoLogger class which prints the information about the current XML and extracts
 * the information like Tagname, Charatcers between the tag of a well formed XML or one that is on
 * track to be a well formed. This class also checks for all the validity constraints via the
 * abstracted class XMLAbstract and throw exceptions in case the XML is not well formed.
 */
public class XMLInfoLogger extends XMLAbstract {

  /**
   * This method should returns a string that represents the parts of the input that have been
   * successfully processed up to this point. - If a start tag <tagname> has been entered, it should
   * add a line Started:tagname in output. - If an end tag </tagname> has been entered, it should
   * add a line Ended:tagname in output. - If there are characters that are not part of a tag name,
   * it should add Characters: followed by the characters verbatim to the output, all on one line
   * (except if the characters include new lines), only if these characters are followed by a valid
   * start or end tag.This includes whitespace characters.
   *
   * @return string which shows the xml tag properties in the above form.
   */
  @Override
  public String output() {
    String output = "";

    for (String item : this.getOutputDataList()) {
      output = output + item + '\n';
    }
    return output;
  }
}
