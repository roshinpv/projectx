export interface ICampaign {
  id?: string;
  name?: string | null;
  owner?: string | null;
  details?: string | null;
  type?: string | null;
}

export const defaultValue: Readonly<ICampaign> = {};
