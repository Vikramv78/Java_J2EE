package org.hiree.salesreports.jdbc.dao.sqlbuilder;

import org.hiree.salesreports.jdbc.dao.sqlbuilder.Evaluation;
public class Condition {
	private String clause;
	private Object value;
	private String column;

	public Condition(String column, Object value, Evaluation evalutation){
		StringBuilder builder = new StringBuilder(" " + column);
		switch (evalutation) {
			case EQUALS:
				builder.append(" = :"+column);
				break;
			case NOT_EQUALS:
				builder.append(" != ? ");
				break;
			case IS_NULL:
				builder.append(" is null ");
				break;
			case NOT_NULL:
				builder.append(" is not null ");
				break;
			case IN:
				builder.append(" in (?) ");
				break;
			case NOT_IN:
				builder.append(" not in (?) ");
				break;
			default:
				builder.append(" = ? ");
		}
		this.clause = builder.toString();
		this.value = value;
		this.column= column;
	}

	public String getClause() {
		return clause;
	}

	public Object getValue() {
		return value;
	}

	public String getColumn() {
		return column;
	}

}
