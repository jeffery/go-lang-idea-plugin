package ro.redeul.google.go.formatter.blocks;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.redeul.google.go.lang.psi.declarations.GoConstSpec;
import ro.redeul.google.go.lang.psi.expressions.GoExpr;

import java.util.Map;

import static ro.redeul.google.go.formatter.blocks.GoBlockUtil.Alignments;
import static ro.redeul.google.go.formatter.blocks.GoBlockUtil.CustomSpacings;

/**
 * <p/>
 * Created on Dec-31-2013 13:00
 *
 * @author <a href="mailto:mtoader@gmail.com">Mihai Toader</a>
 */
public class ConstSpec extends Code<GoConstSpec> {

    public ConstSpec(GoConstSpec psi, CommonCodeStyleSettings settings,
                     Indent indent,
                     Map<Alignments.Key, Alignment> alignmentsMap) {
        super(psi, settings, indent, null, alignmentsMap);

        withCustomSpacing(CustomSpacings.CONST_SPEC);
        withDefaultSpacing(GoBlockUtil.Spacings.SPACE);
    }

    @Override
    protected Alignment getChildAlignment(@NotNull PsiElement child, @Nullable PsiElement prevChild,
                                          Map<Alignments.Key, Alignment> alignments) {

        if (child.getNode().getElementType() == oASSIGN)
            return alignments.get(Alignments.Key.Operator);

        if (child instanceof PsiComment)
            return alignments.get(Alignments.Key.Comments);

        if (child instanceof GoExpr)
            return alignments.get(Alignments.Key.Value);

        return super.getChildAlignment(child, prevChild, alignments);
    }
}