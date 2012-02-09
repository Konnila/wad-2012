package wad.tokkel.models;

import java.util.Date;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "Task")
public class Task extends AbstractModel {
  private static final long serialVersionUID = 1L;

  @Column(name = "description", length = 1024, nullable = false)
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "startedTime", nullable = true)
  private Date startedTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "stoppedTime", nullable = true)
  private Date stoppedTime;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "projectId", nullable = true)
  private Project project;

  @Transient
  private transient Integer projectId;

  @Transient
  private transient boolean start;

  @Transient
  private transient boolean stop;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartedTime() {
    return startedTime;
  }

  public void setStartedTime(Date startedTime) {
    this.startedTime = startedTime;
  }

  public Date getStoppedTime() {
    return stoppedTime;
  }

  public void setStoppedTime(Date stoppedTime) {
    this.stoppedTime = stoppedTime;
  }

  // Estetään loputon rekursio JSON-serialisoinnissa
  @JsonBackReference
  public Project getProject() {
    return project;
  }

  @JsonBackReference
  public void setProject(Project project) {
    this.project = project;
  }

  public Integer getProjectId() {
    if (projectId != null) {
      return projectId;
    }
    if (project != null) {
      return project.getId();
    }

    return null;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }

  public boolean isStart() {
    return start;
  }

  public void setStart(boolean start) {
    this.start = start;
  }

  public boolean isStop() {
    return stop;
  }

  public void setStop(boolean stop) {
    this.stop = stop;
  }
}
