package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAssetTransfer;
import org.compiere.model.MDepreciationWorkfile;
import org.compiere.model.MDocType;
import org.compiere.util.Env;


/**
 * Posting for {@link MAssetTransfer} document. DOCBASETYPE_GLJournal.
 * @author Anca Bradau www.arhipac.ro
 */
public class Doc_AssetTransfer extends Doc 
{

	public Doc_AssetTransfer (MAcctSchema as, ResultSet rs, String trxName)
	{
		super(as, MAssetTransfer.class, rs, MDocType.DOCBASETYPE_GLJournal, trxName);
	}

	@Override
	protected String loadDocumentDetails()
	{
		return null;
	}
	
	@Override
	public BigDecimal getBalance() {
    	return Env.ZERO;
	}
	
	/**
	 * Produce posting:
	 * <pre>
	 *	20.., 21..[A_Asset_New_Acct]			=	23..[A_Asset_Acct]		
	 * </pre>
	 */
	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetTransfer assetTr = getAssetTransfer();
		MDepreciationWorkfile wk = getAssetWorkfile();	
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetTr.getPostingType());
		facts.add(fact);
		//
		// Change Asset Account
		if (assetTr.getA_Asset_New_Acct() != assetTr.getA_Asset_Acct())
		{
			MAccount dr = MAccount.get(getCtx(), assetTr.getA_Asset_New_Acct());  
			MAccount cr = MAccount.get(getCtx(), assetTr.getA_Asset_Acct());
			FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(),
					wk.getA_Asset_Cost(), false);
		}
		//
		// Change Asset Accum. Depr. Account
		if (assetTr.getA_Accumdepreciation_New_Acct() != assetTr.getA_Accumdepreciation_Acct())
		{
			MAccount cr = MAccount.get(getCtx(), assetTr.getA_Accumdepreciation_New_Acct());  
			MAccount dr = MAccount.get(getCtx(), assetTr.getA_Accumdepreciation_Acct());
			FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(),
					wk.getA_Accumulated_Depr(), false);
		}
		//
		return facts;
	}

	/**
	 * @return MAssetTransfer
	 */
	private MAssetTransfer getAssetTransfer()
	{
		return (MAssetTransfer)getPO();
	}
	
	/**
	 * @return MDepreciationWorkfile
	 */
	private MDepreciationWorkfile getAssetWorkfile()
	{
		MAssetTransfer assetTr = getAssetTransfer();
		return MDepreciationWorkfile.get(getCtx(), assetTr.getA_Asset_ID(), assetTr.getPostingType(), getTrxName());
	}
	
}
