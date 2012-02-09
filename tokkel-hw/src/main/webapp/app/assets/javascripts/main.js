var App = {
  Models: {},
  Collections: {},
  Views: {},
  Routers: {},
  init: function() {
    new App.Routers.Main();
    Backbone.history.start();
  }
};

App.Models.Task = Backbone.Model.extend({
  urlRoot: function() {
    var projectId = null;

    if (this.options) {
      projectId = this.options.projectId;
    }

    if (projectId) {
      return "../projects/" + projectId + "/tasks";
    } else {
      return "../tasks";
    }
  }
});

App.Collections.Tasks = Backbone.Collection.extend({
  model: App.Models.Task,

  initialize: function(models, options) {
    this.options = options;
  },

  url: function() {
    var projectId = null;

    if (this.options) {
      projectId = this.options.projectId;
    }

    if (projectId) {
      return "../projects/" + projectId + "/tasks";
    } else {
      return "../tasks";
    }
  }
});

App.Models.Project = Backbone.Model.extend({
  urlRoot: "../projects"
});

App.Collections.Projects = Backbone.Collection.extend({
  url: "../projects",

  initialize: function() {

  }
});

App.Views.TaskListItem = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  events: {
    "click .start": "start",
    "click .stop": "stop",
    "click .delete": "destroy"
  },

  render: function() {
    var attributes = this.model.toJSON();
    if (attributes.startedTime) {
      attributes.startedTime =
        new Date(attributes.startedTime).format("yyyy-mm-dd HH:MM:ss");
    }
    if (attributes.stoppedTime) {
      attributes.stoppedTime =
        new Date(attributes.stoppedTime).format("yyyy-mm-dd HH:MM:ss");
    }
    if (this.options.projectCollection && attributes.projectId) {
      var project = this.options.projectCollection.get(attributes.projectId);
      if (project) {
        attributes.project = project.toJSON();
      }
    }
    var content = Mustache.to_html($("#taskListItemTemplate").html(),
      attributes);
    $(this.el).html(content);

    if (this.model.get("startedTime") != null &&
        this.model.get("stoppedTime") == null) {
      this.updateTime(this);
      window.setInterval(this.updateTime, 1000, this);
    }
    return this;
  },

  updateTime: function(self) {
    var elapsedTimeElement = $(self.el).find(".elapsed");
    var startedTimeMillis = self.model.get("startedTime");
    var elapsedTimeMillis = new Date().getTime() - startedTimeMillis;
    $(elapsedTimeElement).text(Math.round(elapsedTimeMillis / 1000) + " seconds ago");
  },

  start: function() {
    var self = this;
    this.model.set({start: true});
    this.model.save({}, {
      success: function() {
        self.render();
      },
      error: function(model, response) {
        alert("Error updating task state: " +
            response.status + " " + response.statusText);
      }
    });
  },

  stop: function() {
    var self = this;
    this.model.set({stop: true});
    this.model.save({}, {
      success: function() {
        self.render();
      },
      error: function(model, response) {
        alert("Error updating task state: " +
            response.status + " " + response.statusText);
      }
    });
  },

  destroy: function() {
    var self = this;
    this.model.destroy({
      success: function() {
        self.remove();
      },
      error: function(model, response) {
        alert("Error deleting task: " +
            response.status + " " + response.statusText);
      }
    });
  }
});

App.Views.ProjectSelectItem = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  render: function() {
    var content = Mustache.to_html($("#projectSelectItemTemplate").html(),
      this.model.toJSON());
    $(this.el).html(content);

    this.setElement($(this.el).find("option"));

    return this;
  },

  remove: function() {
    
  }
});

App.Views.TaskList = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  render: function() {
    var content = $("#taskListTemplate").html();
    $(this.el).html(content);

    var self = this;

    if (this.options.projectCollection) {
      var projectSelectElement = $(this.el).find("#taskAddProjectSelect");
      this.options.projectCollection.forEach(function(model) {
        var view = new App.Views.ProjectSelectItem({model: model});
        projectSelectElement.append(view.$el);
      });
    }

  $(this.el).find("#taskAddForm").submit(function(event) {
      event.preventDefault();

      var selectedProjectId = $("#taskAddProjectSelect").val();

      var taskDescription = $(self.el).find("#taskAddDescriptionTextField").val();
      if (taskDescription.trim().length < 1) {
        $(self.el).find("#taskAddNotice").html("Task must have a description!");
        $(self.el).find("#taskAddControlGroup").addClass("error");
        return false;
      } else {
        $(self.el).find("#taskAddNotice").html("");
        $(self.el).find("#taskAddControlGroup").removeClass("error");
      }

      var task = new App.Models.Task();
      task.save({ description : taskDescription, projectId: selectedProjectId}, {
        success: function() {
          self.collection.add(task, {at: 0});
        },
        error: function(model, response) {
          $(self.el).find("#taskAddNotice").html("Error adding task: " +
            response.status + " " + response.statusText);
          $(self.el).find("#taskAddControlGroup").addClass("error");
        }
      });

      return false;
    });

    var listElement = $(this.el).find("#taskListTable");

    this.collection.forEach(function(model) {
      var el = $("<tr></tr>");
      listElement.append(el);

      new App.Views.TaskListItem({model: model, el: el, projectCollection: self.options.projectCollection});
    });

    return this;
  }
});

App.Views.ProjectListItem = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  events: {
    "click .delete": "destroy"
  },

  render: function() {
    var attributes = this.model.toJSON();
    if (attributes.creationTime) {
      attributes.creationTime =
        new Date(attributes.creationTime).format("yyyy-mm-dd HH:MM:ss");
    }
    var content = Mustache.to_html($("#projectListItemTemplate").html(),
      attributes);
    $(this.el).html(content);

    return this;
  },

  destroy: function() {
    var self = this;
    this.model.destroy({
      success: function() {
        self.remove();
      },
      error: function(model, response) {
        alert("Error deleting project: " +
            response.status + " " + response.statusText);
      }
    });
  }
});

App.Views.ProjectList = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  render: function() {
    var content = $("#projectListTemplate").html();
    $(this.el).html(content);

    var self = this;

    $(this.el).find("#projectAddForm").submit(function(event) {
      event.preventDefault();

      var projectName = $(self.el).find("#projectAddNameTextField").val();
      if (projectName.trim().length < 1) {
        $(self.el).find("#projectAddNotice").html("Project must have a name!");
        $(self.el).find("#projectAddControlGroup").addClass("error");
        return false;
      } else {
        $(self.el).find("#projectAddNotice").html("");
        $(self.el).find("#projectAddControlGroup").removeClass("error");
      }

      var project = new App.Models.Project();
      project.save({ name : projectName }, {
        success: function() {
          self.collection.add(project, {at: 0});
        },
        error: function(model, response) {
          $(self.el).find("#projectAddNotice").html("Error adding project: " +
            response.status + " " + response.statusText);
          $(self.el).find("#projectAddControlGroup").addClass("error");
        }
      });

      return false;
    });

    var listElement = $(this.el).find("#projectListTable");

    this.collection.forEach(function(model) {
      var el = $("<tr></tr>");
      listElement.append(el);

      new App.Views.ProjectListItem({model: model, el: el});
    });

    return this;
  }
});

App.Views.ProjectNavListItem = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  render: function() {
    var content = Mustache.to_html($("#projectNavListItemTemplate").html(),
      this.model.toJSON());
    $(this.el).html(content);

    this.setElement($(this.el).find("li"));

    return this;
  },

  remove: function() {
    
  }
});

App.Views.ProjectNavList = Backbone.View.extend({
  initialize: function() {
    this.render();
  },

  render: function() {
    var content = $("#projectNavListTemplate").html();
    $(this.el).html(content);

    var containerElement = $(this.el);

    this.collection.forEach(function(model) {
      var view = new App.Views.ProjectNavListItem({model: model});
      containerElement.append(view.$el);
    });

    return this;
  }
});

App.Routers.Main = Backbone.Router.extend({
  routes: {
    "":                     "listTasks",
    "tasks":                "listTasks",
    "projects":             "listProjects",
    "projects/:id":         "listProjectTasks"
  },

  fetchTasks: function(callback, projectId) {
    // TODO: fetch project-specific tasks!
    var tasks = new App.Collections.Tasks({}, {projectId: projectId});
    tasks.fetch({
      success: function() {
        if (callback) {
          callback(tasks);
        }
      },
      error: function(collection, response) {
        alert("Error loading tasks: " +
            response.status + " " + response.statusText);
      }
    });

    return tasks;
  },

  fetchProjects: function(callback) {
    var projects = new App.Collections.Projects();

    projects.fetch({
      success: function() {
        if (callback) {
          callback(projects);
        }
      },
      error: function(collection, response) {
        alert("Error loading projects: " +
            response.status + " " + response.statusText);
      }
    });

    return projects;
  },

  renderTaskList: function(tasks, projects) {
    var taskListView =
      new App.Views.TaskList({collection: tasks, el: $("#mainContent"),
      projectCollection: projects});
    tasks.on("add", function() {
      taskListView.render();
    });
    tasks.on("remove", function() {
      taskListView.render();
    });
  },

  renderProjectNav: function(projects) {
    var projectNavListView = new App.Views.ProjectNavList(
        {collection: projects, el: $("#projectNavList")});

    projects.on("add", function() {
      projectNavListView.render();
    });
    projects.on("remove", function() {
      projectNavListView.render();
    });
  },

  renderProjectList: function(projects) {
    var projectListView = new App.Views.ProjectList(
        {collection: projects, el: $("#mainContent")});

    projects.on("add", function() {
      projectListView.render();
    });
    projects.on("remove", function() {
      projectListView.render();
    });
  },

  listTasks: function() {
    $("#navigation li").removeClass("active");
    $("#tasksNavItem").addClass("active");

    var self = this;

    self.fetchProjects(function(projects) {
      self.renderProjectNav(projects);
      self.fetchTasks(function(tasks) {
        self.renderTaskList(tasks, projects);
      });
    });
  },

  listProjects: function() {
    $("#navigation li").removeClass("active");
    $("#projectsNavItem").addClass("active");

    var self = this;
    self.fetchProjects(function(projects) {
      self.renderProjectNav(projects);
      self.renderProjectList(projects);
    });
  },

  listProjectTasks: function(projectId) {
    $("#navigation li").removeClass("active");

    var self = this;
    self.fetchProjects(function(projects) {
      self.renderProjectNav(projects);
      $("li[data-project-id=\"" + projectId + "\"]").addClass("active");

      // Allow user to select only this specific project when adding tasks
      var selectedProject = projects.get(projectId);
      var selectedProjectCollection =
        new App.Collections.Projects([ selectedProject ]);
      self.fetchTasks(function(tasks) {
        self.renderTaskList(tasks, selectedProjectCollection);
      }, projectId);
    });
  }
});
