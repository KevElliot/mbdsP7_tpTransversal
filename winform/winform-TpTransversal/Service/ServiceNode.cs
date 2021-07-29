using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using winform_TpTransversal.Model;

namespace winform_TpTransversal.Service
{
    public class ServiceNode
    {
        string uri = "https://parilocalnode.herokuapp.com/pariBack/";

        public async Task<(string res,Login utilisateur)> Login(string email, string password)
        {
            Login user = null;
            string res;
            if (string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                res = "Veuillez entrer un email et un password";
                return (res,user);
            }
            var utilisateur = new { email = email, password = password };
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(uri);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.PostAsJsonAsync("auth/login", utilisateur);
                if (Res.IsSuccessStatusCode)
                {
                    var result = Res.Content.ReadAsStringAsync().Result;
                    var jsonData = (JObject)JsonConvert.DeserializeObject(result);
                    user = new Login();
                    user._id = jsonData["_id"].Value<string>();
                    user.name = jsonData["name"].Value<string>();
                    user.jetons = jsonData["jetons"].Value<int>();
                    user.email = email;
                    user.isConnecte = true;
                    Console.WriteLine("USER : " + JsonConvert.SerializeObject(user));
                    res = "valide";
                    return (res, user);
                }
                else
                    
                    res = "Verifier votre adresse et votre mot de passe. Status: "+ Res.StatusCode.ToString();
                    return (res, user);
            }
        }
        public async Task<string> DemandeJeton(string iduser, int jetonsdemande)
        {
            if (jetonsdemande <= 0)
            {
                return "Veuillez entrer votre demande";
            }
            var demande = new { iduser = iduser, jetonsdemande = jetonsdemande, statut = false };
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(uri);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.PostAsJsonAsync("jeton/demande", demande);
                if (Res.IsSuccessStatusCode)
                {
                    return "200";
                }
                else
                    return Res.StatusCode.ToString();
            }
        }
        public async Task<string> UpdateUser(string id, string name, string password, string email, string role, float jetons)
        {
            if (string.IsNullOrEmpty(name) || string.IsNullOrEmpty(email) || string.IsNullOrEmpty(password))
            {
                return "900";
            }
            var user = new { _id = id, name = name, password = password, email = email, role = role, jetons = jetons };
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(uri);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage Res = await client.PutAsJsonAsync("user", user);
                if (Res.IsSuccessStatusCode)
                {
                    return "200";
                }
                else 
                {
                    Console.WriteLine(Res.StatusCode);
                    return Res.StatusCode.ToString();
                };
            }
        }
        public async Task<Login> GetUserById(string idUser)
        {
            Login user = null;
            try
            {
                using (var client = new HttpClient())
                {
                    client.BaseAddress = new Uri(uri);
                    HttpResponseMessage response = await client.GetAsync("users/" + idUser);
                    if (response.IsSuccessStatusCode)
                    {
                        user = await response.Content.ReadAsAsync<Login>();
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            //Console.WriteLine("JSON SERIALIZE : " + JsonConvert.SerializeObject(hist));
            return user;
        }

        public async Task<string> Inscription(Login user)
        {
            var newUser = new { name = user.name, password = user.password, email = user.email, role=user.role, jetons = user.jetons };
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri(uri);
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                Console.WriteLine(JsonConvert.SerializeObject(newUser, Newtonsoft.Json.Formatting.None, new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore }));

                HttpResponseMessage Res = await client.PostAsJsonAsync("auth/register", newUser);
                if (Res.IsSuccessStatusCode)
                {
                    return "200";
                }
                else
                    Console.WriteLine(Res);
                    return Res.StatusCode.ToString();
            }
        }
    }
}
