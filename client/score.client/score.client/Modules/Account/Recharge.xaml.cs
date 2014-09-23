using Newtonsoft.Json;
using score.client.Models;
using score.client.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client.Modules.Account
{
    public partial class Recharge : UserControl
    {
        WebClient clientChange = new WebClient { Encoding = Encoding.UTF8 };
        WebClient clientSearch = new WebClient { Encoding = Encoding.UTF8 };
        public Recharge()
        {
            InitializeComponent();
            this.Loaded += Recharge_Loaded;
        }

        void Recharge_Loaded(object sender, RoutedEventArgs e)
        {
            clientChange.DownloadStringCompleted += clientChange_DownloadStringCompleted;
            clientSearch.DownloadStringCompleted += clientSearch_DownloadStringCompleted;
        }

        void clientSearch_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            if (e.Error == null)
            {
                var userAccount = JsonConvert.DeserializeObject<UserAccount>(e.Result);
                if (userAccount != null)
                {
                    txtScoreSum.Text = (userAccount.score + userAccount.frozenScore).ToString();
                    txtScoreUse.Text = userAccount.score.ToString();
                }
                else
                {
                    MessageBox.Show("没有此用户信息");
                }
            }
        }

        void clientChange_DownloadStringCompleted(object sender, DownloadStringCompletedEventArgs e)
        {
            if (e.Error == null)
            {
                try
                {
                    var userAccount = JsonConvert.DeserializeObject<UserAccount>(e.Result);
                    if (userAccount != null)
                    {
                        txtScoreSum.Text = (userAccount.score + userAccount.frozenScore).ToString();
                        txtScoreUse.Text = userAccount.score.ToString();
                        MessageBox.Show("操作成功");
                    }

                }
                catch (Exception ex)
                {
                    MessageBox.Show("操作失败");
                }
            }
        }


        private void btnCancle_Click(object sender, RoutedEventArgs e)
        {
            txtRechargeValue.Text = "";
            txtfees.Text = "";
            txtAmountMoney.Text = "";
        }

        private void btnRecharge_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (txtUserName.Text.Trim().Length > 0 && txtRechargeValue.Text.Trim().Length > 0)
                    clientChange.DownloadStringAsync(new Uri(ProjectApiHelper.UrlScoreChange + "?username=" + txtUserName.Text.Trim() + "&score=" + Convert.ToInt32(txtRechargeValue.Text.Trim()) + "&oper=" + Const.OPER_INC));
            }
            catch (Exception ex)
            {
            }
        }

        private void btnWithDraw_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (txtDraw.Text.Trim().Length > 0)
                {
                    if (Convert.ToInt32(txtDraw.Text.Trim()) <= Convert.ToInt32(txtScoreUse.Text.Trim()))
                        clientChange.DownloadStringAsync(new Uri(ProjectApiHelper.UrlScoreChange + "?username=" + txtUserName.Text.Trim() + "&score=" + Convert.ToInt32(txtDraw.Text.Trim()) + "&oper=" + Const.OPER_DEC));
                    else
                    {
                        MessageBox.Show("积分不足");
                    }
                }
            }
            catch (Exception ex)
            {
            }
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            clientSearch.DownloadStringAsync(new Uri(ProjectApiHelper.UrlBalanceValue + "?username=" + txtUserName.Text.Trim()));
        }

        private void txtRechargeValue_LostFocus(object sender, RoutedEventArgs e)
        {
            try
            {
                if (txtRechargeValue.Text.Length > 0)
                {
                    txtfees.Text = (Convert.ToInt32(txtRechargeValue.Text.Trim()) * 0.15).ToString();
                    txtAmountMoney.Text = (Convert.ToInt32(txtRechargeValue.Text.Trim()) * 1.15).ToString();
                }
            }
            catch (Exception ex)
            {
            }
        }

        private void txtDraw_LostFocus(object sender, RoutedEventArgs e)
        {
            try
            {
                if (Convert.ToInt32(txtDraw.Text.Trim()) > 0)
                    txtDrawRMB.Text = (Convert.ToDouble(txtDraw.Text.Trim())).ToString();
            }
            catch (Exception ex)
            {
            }
        }

    }
}
