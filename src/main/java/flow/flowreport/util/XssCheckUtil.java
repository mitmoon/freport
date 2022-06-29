package flow.flowreport.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import flow.flowreport.api.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import java.util.HashMap;
import java.util.Map;

public class XssCheckUtil extends CharacterEscapes {
    private final int[] escaped;
    private final transient CharSequenceTranslator translator;

    public XssCheckUtil() {
        this.escaped = CharacterEscapes.standardAsciiEscapesForJSON();
        Map<CharSequence, CharSequence> charSequenceMap = new HashMap<>();
        charSequenceMap.put("#", "&#35;");
        charSequenceMap.put("'", "&#39;");
        charSequenceMap.put("(", "&#40;");
        charSequenceMap.put(")", "&#41;");

        translator = new AggregateTranslator(
                new LookupTranslator(EntityArrays.BASIC_ESCAPE),
                new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
                new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE),
                new LookupTranslator(charSequenceMap)
        );
        escaped['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['('] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['\''] = CharacterEscapes.ESCAPE_CUSTOM;
        escaped['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return escaped;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        char charAt = (char) ch;

        return new SerializedString(translator.translate(Character.toString(charAt)));
    }

    public static String findIllegalKeyword(String source) {
        if (StringUtils.isNotBlank(source))
        {
            for (String temp : xssIllegalKeywords)
            {
                if (StringUtils.containsIgnoreCase(source, temp))
                    return temp;
            }
        }

        return null;
    }

    public static void checkIllegalKeyword(String[] list) {
        for (String temp : list)
        {
            String keyword = findIllegalKeyword(temp);
            if (keyword != null)
                throw new ApiException("XSS 키워드는 사용할수 없습니다.");
        }
    }

    private static final String[] xssIllegalKeywords = {
            "<script>",
            "alert",
            "javascript",
            "style",
            "\\\";",
            ";//",
            "<htmlxmln",
            "echo("
    };
}
