
namespace winform_TpTransversal.Vu
{
    partial class Profil
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.groupDemande = new System.Windows.Forms.GroupBox();
            this.annulerBtn = new System.Windows.Forms.Button();
            this.passwordTextBox = new System.Windows.Forms.TextBox();
            this.emailTextBox = new System.Windows.Forms.TextBox();
            this.nomTextbox = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.modifierBtn = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.groupDemande.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupDemande
            // 
            this.groupDemande.BackColor = System.Drawing.Color.Transparent;
            this.groupDemande.Controls.Add(this.annulerBtn);
            this.groupDemande.Controls.Add(this.passwordTextBox);
            this.groupDemande.Controls.Add(this.emailTextBox);
            this.groupDemande.Controls.Add(this.nomTextbox);
            this.groupDemande.Controls.Add(this.label4);
            this.groupDemande.Controls.Add(this.label3);
            this.groupDemande.Controls.Add(this.label2);
            this.groupDemande.Controls.Add(this.modifierBtn);
            this.groupDemande.Controls.Add(this.label1);
            this.groupDemande.Controls.Add(this.label6);
            this.groupDemande.Location = new System.Drawing.Point(10, 33);
            this.groupDemande.Name = "groupDemande";
            this.groupDemande.Size = new System.Drawing.Size(334, 291);
            this.groupDemande.TabIndex = 22;
            this.groupDemande.TabStop = false;
            this.groupDemande.Text = "Votre profil";
            // 
            // annulerBtn
            // 
            this.annulerBtn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.annulerBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.annulerBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.annulerBtn.Font = new System.Drawing.Font("Rockwell", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.annulerBtn.Location = new System.Drawing.Point(6, 185);
            this.annulerBtn.Name = "annulerBtn";
            this.annulerBtn.Size = new System.Drawing.Size(158, 33);
            this.annulerBtn.TabIndex = 27;
            this.annulerBtn.Text = "Annuler";
            this.annulerBtn.UseVisualStyleBackColor = false;
            this.annulerBtn.Click += new System.EventHandler(this.annulerBtn_Click);
            // 
            // passwordTextBox
            // 
            this.passwordTextBox.BackColor = System.Drawing.SystemColors.ControlLight;
            this.passwordTextBox.Location = new System.Drawing.Point(119, 130);
            this.passwordTextBox.Name = "passwordTextBox";
            this.passwordTextBox.Size = new System.Drawing.Size(168, 20);
            this.passwordTextBox.TabIndex = 26;
            // 
            // emailTextBox
            // 
            this.emailTextBox.BackColor = System.Drawing.SystemColors.ControlLight;
            this.emailTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.emailTextBox.Location = new System.Drawing.Point(119, 90);
            this.emailTextBox.Name = "emailTextBox";
            this.emailTextBox.Size = new System.Drawing.Size(168, 20);
            this.emailTextBox.TabIndex = 25;
            // 
            // nomTextbox
            // 
            this.nomTextbox.BackColor = System.Drawing.SystemColors.ControlLight;
            this.nomTextbox.Location = new System.Drawing.Point(119, 45);
            this.nomTextbox.Name = "nomTextbox";
            this.nomTextbox.Size = new System.Drawing.Size(168, 20);
            this.nomTextbox.TabIndex = 24;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(27, 153);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(237, 12);
            this.label4.TabIndex = 23;
            this.label4.Text = "Entrer un nouveau mot de passe si vous voulez changer.";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(26, 133);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(56, 13);
            this.label3.TabIndex = 22;
            this.label3.Text = "Password:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(47, 90);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(35, 13);
            this.label2.TabIndex = 21;
            this.label2.Text = "Email:";
            // 
            // modifierBtn
            // 
            this.modifierBtn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.modifierBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.modifierBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.modifierBtn.Font = new System.Drawing.Font("Rockwell", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.modifierBtn.Location = new System.Drawing.Point(170, 185);
            this.modifierBtn.Name = "modifierBtn";
            this.modifierBtn.Size = new System.Drawing.Size(158, 33);
            this.modifierBtn.TabIndex = 20;
            this.modifierBtn.Text = "Modifier";
            this.modifierBtn.UseVisualStyleBackColor = false;
            this.modifierBtn.Click += new System.EventHandler(this.modifierBtn_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(89, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(141, 18);
            this.label1.TabIndex = 17;
            this.label1.Text = "Vos informations:\r\n";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(50, 48);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(32, 13);
            this.label6.TabIndex = 16;
            this.label6.Text = "Nom:";
            // 
            // Profil
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(355, 336);
            this.Controls.Add(this.groupDemande);
            this.MaximizeBox = false;
            this.Name = "Profil";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Profil";
            this.Load += new System.EventHandler(this.Profil_Load);
            this.groupDemande.ResumeLayout(false);
            this.groupDemande.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupDemande;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button modifierBtn;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label6;
        public System.Windows.Forms.TextBox passwordTextBox;
        public System.Windows.Forms.TextBox emailTextBox;
        public System.Windows.Forms.TextBox nomTextbox;
        private System.Windows.Forms.Button annulerBtn;
    }
}