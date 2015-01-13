/*
* generated by Xtext
*/
package insa.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import insa.services.LegoGrammarAccess;

public class LegoParser extends AbstractContentAssistParser {
	
	@Inject
	private LegoGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected insa.ui.contentassist.antlr.internal.InternalLegoParser createParser() {
		insa.ui.contentassist.antlr.internal.InternalLegoParser result = new insa.ui.contentassist.antlr.internal.InternalLegoParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getExpressionAccess().getAlternatives(), "rule__Expression__Alternatives");
					put(grammarAccess.getBoolExpAccess().getAlternatives(), "rule__BoolExp__Alternatives");
					put(grammarAccess.getCommandAccess().getAlternatives(), "rule__Command__Alternatives");
					put(grammarAccess.getBOOLEAN_LITERALAccess().getAlternatives(), "rule__BOOLEAN_LITERAL__Alternatives");
					put(grammarAccess.getWhileLoopAccess().getGroup(), "rule__WhileLoop__Group__0");
					put(grammarAccess.getNegExpAccess().getGroup(), "rule__NegExp__Group__0");
					put(grammarAccess.getAndExpAccess().getGroup(), "rule__AndExp__Group__0");
					put(grammarAccess.getIfBlockAccess().getGroup(), "rule__IfBlock__Group__0");
					put(grammarAccess.getIfBlockAccess().getGroup_4(), "rule__IfBlock__Group_4__0");
					put(grammarAccess.getBipAccess().getGroup(), "rule__Bip__Group__0");
					put(grammarAccess.getHasTurnedCmdAccess().getGroup(), "rule__HasTurnedCmd__Group__0");
					put(grammarAccess.getMoveCmdAccess().getGroup(), "rule__MoveCmd__Group__0");
					put(grammarAccess.getPrintCmdAccess().getGroup(), "rule__PrintCmd__Group__0");
					put(grammarAccess.getSetTurnAngleCmdAccess().getGroup(), "rule__SetTurnAngleCmd__Group__0");
					put(grammarAccess.getStopEngineCmdAccess().getGroup(), "rule__StopEngineCmd__Group__0");
					put(grammarAccess.getStopProgramCmdAccess().getGroup(), "rule__StopProgramCmd__Group__0");
					put(grammarAccess.getTurnCmdAccess().getGroup(), "rule__TurnCmd__Group__0");
					put(grammarAccess.getObstacleCmdAccess().getGroup(), "rule__ObstacleCmd__Group__0");
					put(grammarAccess.getProgramUnitAccess().getBlockAssignment(), "rule__ProgramUnit__BlockAssignment");
					put(grammarAccess.getWhileLoopAccess().getLoopCondAssignment_1(), "rule__WhileLoop__LoopCondAssignment_1");
					put(grammarAccess.getWhileLoopAccess().getBlockAssignment_3(), "rule__WhileLoop__BlockAssignment_3");
					put(grammarAccess.getNegExpAccess().getExpAssignment_1(), "rule__NegExp__ExpAssignment_1");
					put(grammarAccess.getAndExpAccess().getLeftExpAssignment_1(), "rule__AndExp__LeftExpAssignment_1");
					put(grammarAccess.getAndExpAccess().getRightExpAssignment_2(), "rule__AndExp__RightExpAssignment_2");
					put(grammarAccess.getIfBlockAccess().getConditionAssignment_1(), "rule__IfBlock__ConditionAssignment_1");
					put(grammarAccess.getIfBlockAccess().getThenBlockAssignment_3(), "rule__IfBlock__ThenBlockAssignment_3");
					put(grammarAccess.getIfBlockAccess().getElseBlockAssignment_4_1(), "rule__IfBlock__ElseBlockAssignment_4_1");
					put(grammarAccess.getBipAccess().getDurationAssignment_4(), "rule__Bip__DurationAssignment_4");
					put(grammarAccess.getBipAccess().getPowerAssignment_8(), "rule__Bip__PowerAssignment_8");
					put(grammarAccess.getBipAccess().getRepetAssignment_12(), "rule__Bip__RepetAssignment_12");
					put(grammarAccess.getHasTurnedCmdAccess().getAngleAssignment_4(), "rule__HasTurnedCmd__AngleAssignment_4");
					put(grammarAccess.getMoveCmdAccess().getPowerAssignment_4(), "rule__MoveCmd__PowerAssignment_4");
					put(grammarAccess.getPrintCmdAccess().getMsgAssignment_4(), "rule__PrintCmd__MsgAssignment_4");
					put(grammarAccess.getPrintCmdAccess().getDurationAssignment_8(), "rule__PrintCmd__DurationAssignment_8");
					put(grammarAccess.getPrintCmdAccess().getLineAssignment_12(), "rule__PrintCmd__LineAssignment_12");
					put(grammarAccess.getPrintCmdAccess().getColAssignment_16(), "rule__PrintCmd__ColAssignment_16");
					put(grammarAccess.getSetTurnAngleCmdAccess().getAngleAssignment_4(), "rule__SetTurnAngleCmd__AngleAssignment_4");
					put(grammarAccess.getTurnCmdAccess().getPowerAssignment_4(), "rule__TurnCmd__PowerAssignment_4");
					put(grammarAccess.getTurnCmdAccess().getAngleAssignment_8(), "rule__TurnCmd__AngleAssignment_8");
					put(grammarAccess.getObstacleCmdAccess().getDistanceAssignment_4(), "rule__ObstacleCmd__DistanceAssignment_4");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			insa.ui.contentassist.antlr.internal.InternalLegoParser typedParser = (insa.ui.contentassist.antlr.internal.InternalLegoParser) parser;
			typedParser.entryRuleProgramUnit();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_SL_COMMENT", "RULE_ML_COMMENT", "RULE_WS" };
	}
	
	public LegoGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(LegoGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
