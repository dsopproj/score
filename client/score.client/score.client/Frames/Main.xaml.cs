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
    public partial class Main : UserControl
    {
        private static Main current;

        public Main()
        {
            // 为初始化变量所必需
            InitializeComponent();
            current = this;
        }

        public static Main GetCurrent()
        {
            return current;
        }

        public void SetLayoutRoot(UserControl layoutRoot)
        {
            this.LayoutRoot.Children.Clear();
            this.LayoutRoot.Children.Add(layoutRoot);
        }
    }
}