require_relative 'config.rb'
class Player
  attr_reader :current_place, :balance, :lands, :tools, :tool_quantity
  attr_accessor :status, :points

  def initialize *args
    unless args.empty?
      hash = args[-1]
      hash.each do |k, v|
        instance_variable_set("@#{k}", v)
      end
    end
    @status ||= :wait_for_command
    @lands = []
    @tools = Hash.new(0)
    @tool_quantity = 0
  end

  def execute(command)
    @last_executed = command
    @status = command.execute(self)
  end

  def respond(response)
    @status = @last_executed.execute_with(response)
  end

  def move_to(target)
    @current_place = target
  end

  def buy_land(land)
    if (balance >= land.price && land.owner.nil?)
      @lands << land
      @balance -= land.price
      land.owner = self
      true
    else
      false
    end
  end

  def upgrade(land)
    if (@balance >= land.price && land.owner == self)
      land.upgrade
      @balance -= land.price
      true
    else
      false
    end
  end

  def buy_tool(tool)
    if (@points >= tool.points && @tool_quantity < ToolConf::MAX_QUANTITY)
      @tools[tool] += 1
      @tool_quantity += 1
      @points -= tool.points
      true
    else
      false
    end
  end

end
