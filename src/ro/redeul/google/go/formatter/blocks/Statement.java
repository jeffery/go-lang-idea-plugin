package ro.redeul.google.go.formatter.blocks;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Indent;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ro.redeul.google.go.lang.psi.statements.GoStatement;

import java.util.Map;

/**
 * Basic statement formatting block. Will align trailing comments properly
 *
 * <p/>
 * Created on Jan-04-2014 01:16
 *
 * @author <a href="mailto:mtoader@gmail.com">Mihai Toader</a>
 */
public class Statement<Statement extends GoStatement> extends Code<Statement> {

    public Statement(@NotNull Statement node,
                     CommonCodeStyleSettings settings,
                     Indent indent, @NotNull Map<GoBlockUtil.Alignments.Key, Alignment> alignsToUse) {
        super(node, settings, indent, null, alignsToUse);
        withDefaultSpacing(GoBlockUtil.Spacings.SPACE);
    }

    @Nullable
    @Override
    protected Alignment getChildAlignment(@NotNull PsiElement child, @Nullable PsiElement prevChild,
                                          Map<GoBlockUtil.Alignments.Key, Alignment> alignments) {

        if ( child instanceof PsiComment )
            return alignments.get(GoBlockUtil.Alignments.Key.Comments);

        return super.getChildAlignment(child, prevChild, alignments);
    }
}