using score.client.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client.Modules.Config
{
    public partial class UriConfig : UserControl
    {
        public UriConfig()
        {
            InitializeComponent();
            this.Loaded += UriConfig_Loaded;
        }

        void UriConfig_Loaded(object sender, RoutedEventArgs e)
        {

            this.txtUrl.Text = ProjectApiHelper.GetBaseUrl();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            ProjectApiHelper.SetBaseUrl(this.txtUrl.Text.Trim());
        }
    }
}
