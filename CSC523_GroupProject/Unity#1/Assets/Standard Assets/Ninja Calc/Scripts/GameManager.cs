using System;
using UnityEngine;
using System.Collections.Generic;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{

    public static GameManager GM = null;
    public AudioSource audioSource;
    public GameObject Door_Indicator;
    public Text m_Score;
    public Text m_Health;
    private int health;
    private int score;
    private string sceneName;
    private int Key;
    public CountKey Keynumber;

    private void Awake()
    {
        if (GM == null)
        {
            //if not, set instance to this
            GM = this;
        }
    }


    void Start()
    {
        Key = Keynumber.keycount;
        // Create a temporary reference to the current scene.
        Scene currentScene = SceneManager.GetActiveScene();
        sceneName = currentScene.name;

        Door_Indicator.gameObject.SetActive(false);

        health = PlayerPrefs.GetInt("health", health);
        score = PlayerPrefs.GetInt("score", score);
        audioSource = Camera.main.GetComponent<AudioSource>();
        m_Score.text = "score: " + score.ToString();
        m_Health.text = "health: " + health.ToString() + "%";
    }

    void Update()
    {
        if (score >=200 && sceneName == "Level_0" )
        {
            Door_Indicator.gameObject.SetActive(true);
        }
        else if (score >=500 && sceneName == "Level_1")
        {
            Door_Indicator.gameObject.SetActive(true);
        }
        else if (score >= 700 && sceneName == "Level_2")
        {
            Door_Indicator.gameObject.SetActive(true);
        }
        else
            Door_Indicator.gameObject.SetActive(false);
        if (Input.GetKeyDown(KeyCode.M) && audioSource.isPlaying)
        {
            audioSource.Stop();
        }
        else if (Input.GetKeyDown(KeyCode.M) && !audioSource.isPlaying)
        {
            audioSource.Play();
        }

        if (Key <= 0 && score < 200 && sceneName == "Level_0")
            SceneManager.LoadScene("CloseScene");
        else if (Key <= 0 && score < 500 && sceneName == "Level_1")
            SceneManager.LoadScene("CloseScene");
        else if (Key <= 0 && score < 700 && sceneName == "Level_2")
            SceneManager.LoadScene("CloseScene");
    }
     
    public void Music()
    {
        if (audioSource.isPlaying)
        {
            audioSource.Stop();
        }
        else
        {
            audioSource.Play();
        }
    }

    public void ReturnMainMenu()
    {
        SceneManager.LoadScene("OpenScene");
    }

    public void isCorrectAnswer_static()
    {
        score += 100;
        FindObjectOfType<StaticQuestion>().disableQuestionBox_static();
        FindObjectOfType<AudioManager>().Play("Score");
        displayScore();
        Key--;
    }
    public void isWrongAnswer_static()
    {
        score -= 100;
        FindObjectOfType<StaticQuestion>().disableQuestionBox_static();
        displayScore();
        Key--;
    }

    public void isCorrectAnswer_dynamic()
    {
        score += 100;
        FindObjectOfType<QuestionManager>().disableQuestion_dynamic();
        FindObjectOfType<AudioManager>().Play("Score");
        displayScore();
        Key--;
    }

    public void isWrongAnswer_dynamic()
    {
        score -= 100;
        FindObjectOfType<QuestionManager>().disableQuestion_dynamic();
        displayScore();
        Key--;
    }

    private void displayScore()
    {
        m_Score = GameObject.Find("ScoreUpdate").GetComponent<Text>();
        m_Score.text = "score: " + score.ToString();
        
    }

    private void displayHealth()
    {
        m_Health = GameObject.Find("HealthUpdate").GetComponent<Text>();
        m_Health.text = "health: " + health.ToString() + "%";
    }
    private void OnTriggerEnter2D(Collider2D Other)
    {
        if (Other.tag == "enemy")
        {
            FindObjectOfType<Player_Movement>().Backup();
            health = health - 10;
            displayHealth();

        }
        else if (Other.tag == "Level_1" && score >=200 )
        {
            PlayerPrefs.SetInt("health", health);
            PlayerPrefs.SetInt("score", score);
            SceneManager.LoadScene("Level_1");
        }
        else if (Other.tag == "Level_2" && score >= 400)
        {
            PlayerPrefs.SetInt("health", health);
            PlayerPrefs.SetInt("score", score);
            SceneManager.LoadScene("Level_2");
        }
        else if (Other.tag == "Level_3" && score >= 500)
        {
            PlayerPrefs.SetInt("health", health);
            PlayerPrefs.SetInt("score", score);
            SceneManager.LoadScene("CloseScene");
        }

        if (health <= 0 )
        {
            PlayerPrefs.SetInt("health", health);
            PlayerPrefs.SetInt("score", score);
            SceneManager.LoadScene("CloseScene");
        }

    }
}
