using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace score.client
{
	public partial class Top : UserControl
	{
		public Top()
		{
			// 为初始化变量所必需
			InitializeComponent();
		}

        private void btnLogout_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Exist!");
            
        }

        private void btnSetting_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(" to do  setting ...");
        }
	}
}