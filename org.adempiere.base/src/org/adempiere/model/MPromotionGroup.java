/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.X_M_PromotionGroup;

/**
 *
 * @author hengsin
 *
 */
public class MPromotionGroup extends X_M_PromotionGroup {

	private static final long serialVersionUID = 4203915332775348579L;

    /**
    * UUID based Constructor
    * @param ctx  Context
    * @param M_PromotionGroup_UU  UUID key
    * @param trxName Transaction
    */
    public MPromotionGroup(Properties ctx, String M_PromotionGroup_UU, String trxName) {
        super(ctx, M_PromotionGroup_UU, trxName);
    }

    /**
     * @param ctx
     * @param M_PromotionGroup_ID
     * @param trxName
     */
	public MPromotionGroup(Properties ctx, int M_PromotionGroup_ID, String trxName) {
		super(ctx, M_PromotionGroup_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MPromotionGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 * @param M_PromotionGroup_ID
	 * @param trxName
	 * @param virtualColumns
	 */
	public MPromotionGroup(Properties ctx, int M_PromotionGroup_ID, String trxName, String... virtualColumns) {
		super(ctx, M_PromotionGroup_ID, trxName, virtualColumns);
	}

}
