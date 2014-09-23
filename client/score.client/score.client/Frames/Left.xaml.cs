using score.client.Modules.Account;
using score.client.Modules.Advertis;
using score.client.Modules.Cash;
using score.client.Modules.Publish;
using System.Windows;
using System.Windows.Controls;
using System;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client
{
    public partial class Left : UserControl
    {
        private Recharge recharge;
        private Publish publish;
        private ValidateOrder validateOrder;
        private ProductList productList;
        private Advertisement advertisement;
        private AdvertisementConfig advertisementConfig;

        public Left()
        {
            // 为初始化变量所必需
            InitializeComponent();
            recharge = new Recharge();
            //publish = new Publish();
            //validateOrder = new ValidateOrder();
            //productList = new ProductList();
            //advertisement = new Advertisement();
            //advertisementConfig = new AdvertisementConfig();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Main.GetCurrent().SetLayoutRoot(recharge);

        }

        private void btnAccount_Click(object sender, RoutedEventArgs e)
        {
            recharge = new Recharge();
            Main.GetCurrent().SetLayoutRoot(recharge);
        }

        private void btnCash_Click(object sender, RoutedEventArgs e)
        {
            validateOrder = new ValidateOrder();
            Main.GetCurrent().SetLayoutRoot(validateOrder);
        }

        private void btnCashList_Click(object sender, RoutedEventArgs e)
        {
            
            Main.GetCurrent().SetLayoutRoot(new OrderList());
        }

        //发布商品
        private void btnPublish_Click(object sender, RoutedEventArgs e)
        {
            publish = new Publish();
            Main.GetCurrent().SetLayoutRoot(publish);
        }

        //商品列表
        private void btnProductList_Click(object sender, RoutedEventArgs e)
        {
            productList = new ProductList();
            Main.GetCurrent().SetLayoutRoot(productList);
        }



        private void btnAdvertisement_Click(object sender, RoutedEventArgs e)
        {
            advertisement = new Advertisement();
            Main.GetCurrent().SetLayoutRoot(advertisement);
        }

        private void btnAdvertisementConfig_Click(object sender, RoutedEventArgs e)
        {
            advertisementConfig = new AdvertisementConfig();
            Main.GetCurrent().SetLayoutRoot(advertisementConfig);
        }

        private void btnConfigUrl_Click(object sender, RoutedEventArgs e)
        {
            Main.GetCurrent().SetLayoutRoot(new score.client.Modules.Config.UriConfig());
        }


    }
}