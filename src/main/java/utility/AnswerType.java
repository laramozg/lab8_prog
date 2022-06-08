package utility;

import java.io.Serializable;

public enum AnswerType implements Serializable {
    SUCCESSFULLY,
    UNSUCCESSFULLY,
    NEED_ELEMENT,
    NEED_PARAMETER,
    RETURN_ACTION,
    GETTER_STATE,
    DIALOG_STATE,
    EXIT
}